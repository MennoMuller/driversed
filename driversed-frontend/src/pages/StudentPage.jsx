import React, { useEffect, useState } from "react";
import ScheduleList from "../components/ScheduleList";
import StudentPageInput from "../components/StudentPageInput";

function StudentPage(props) {
  const [instructor, setInstructor] = useState(0);
  const [student, setStudent] = useState(0);
  const [lessons, setLessons] = useState([]);
  const [slots, setSlots] = useState([]);
  const [date, setDate] = useState("");
  const currDate = new Date();
  currDate.setHours(1, 0, 0, 0);

  useEffect(() => {
    if (student != 0) {
      getFutureLessons();
    } else {
      setLessons([]);
    }
  }, [student]);

  useEffect(() => {
    if (instructor != 0) {
      getAvailableSlots();
    } else {
      setSlots([]);
    }
  }, [instructor]);

  function sortByTime(a, b) {
    // Turn your strings into dates, and then subtract them
    // to get a value that is either negative, positive, or zero.
    return new Date(a.time) - new Date(b.time);
  }

  function getFutureLessons() {
    fetch(
      `http://localhost:8082/api/student/${student}/schedule`
    )
      .then((response) => response.json())
      .then((data) => setLessons(data.sort(sortByTime)));
  }

  function getAvailableSlots() {
    fetch(
      `http://localhost:8082/api/instructor/${instructor}/slots`
    )
      .then((response) => response.json())
      .then((data) => setSlots(data.sort(sortByTime)));
  }

  const bookLesson = (lessonId) => {
    fetch(
      `http://localhost:8082/api/student/${student}/reserve/${lessonId}`,
      { method: "PUT" }
    ).then(() => {
      getAvailableSlots();
      getFutureLessons();
    });
  };

  const cancelLesson = (lessonId) => {
    fetch(
      `http://localhost:8082/api/lesson/${lessonId}/cancel/student`,
      { method: "PUT" }
    ).then(() => {
      getAvailableSlots();
      getFutureLessons();
    });
  };

  const filterByDate = (lesson) => {
    const lessonDate = new Date(lesson.time).toDateString();
    const selectedDate = new Date(date).toDateString();
    return lessonDate == selectedDate;
  };

  return (
    <div>
      <h2 className="text-4xl font-bold">Student</h2>
      <div className="flex flex-row justify-evenly">
        <StudentPageInput
          students={props.students}
          instructors={props.instructors}
          student={student}
          setStudent={setStudent}
          instructor={instructor}
          setInstructor={setInstructor}
          date={date}
          setDate={setDate}
        />
        <div>
          <h3 className="mb-2 text-2xl font-bold">
            Book Lessons
          </h3>
          <ScheduleList
            lessons={slots.filter(filterByDate)}
            instructorPerspective={false}
            onClick={bookLesson}
          />
        </div>
        <div>
          <h3 className="mb-2 text-2xl font-bold">
            Your Lessons
          </h3>
          <ScheduleList
            lessons={lessons}
            instructorPerspective={false}
            onClick={cancelLesson}
          />
        </div>
      </div>
    </div>
  );
}
export default StudentPage;
