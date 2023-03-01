import React, { useEffect, useState } from "react";
import ScheduleList from "../components/ScheduleList";

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

  function getFutureLessons() {
    fetch(
      `http://localhost:8082/api/student/${student}/schedule`
    )
      .then((response) => response.json())
      .then((data) =>
        setLessons(
          data
            .filter((lesson) => {
              const lessonDate = Date.parse(lesson.time);
              const currentDate = currDate.valueOf();
              return lessonDate >= currentDate;
            })
            .sort(function (a, b) {
              // Turn your strings into dates, and then subtract them
              // to get a value that is either negative, positive, or zero.
              return new Date(a.time) - new Date(b.time);
            })
        )
      );
  }

  function getAvailableSlots() {
    fetch(
      `http://localhost:8082/api/instructor/${instructor}/slots`
    )
      .then((response) => response.json())
      .then((data) =>
        setSlots(
          data.sort(function (a, b) {
            // Turn your strings into dates, and then subtract them
            // to get a value that is either negative, positive, or zero.
            return new Date(a.time) - new Date(b.time);
          })
        )
      );
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

  return (
    <div>
      <h2 className="text-4xl font-bold">Student</h2>
      <div className="flex flex-row justify-evenly">
        <div className="flex flex-col">
          <select
            name="student"
            id="student"
            value={student}
            onChange={(e) => {
              setStudent(e.target.value);
            }}
          >
            <option value="0">Select Student</option>
            {props.students.map((item) => (
              <option key={item.id} value={item.id}>
                {item.name}
              </option>
            ))}
          </select>
          <select
            name="instructor"
            id="instructor"
            value={instructor}
            onChange={(e) => {
              setInstructor(e.target.value);
            }}
          >
            <option value="0">Select Instructor</option>
            {props.instructors.map((item) => (
              <option key={item.id} value={item.id}>
                {item.name}
              </option>
            ))}
          </select>
          <input
            type="date"
            name="date"
            id="date"
            value={date}
            onChange={(e) => {
              setDate(e.target.value);
            }}
          />
        </div>
        <div>
          <h3 className="mb-2 text-2xl font-bold">
            Book Lessons
          </h3>
          <ScheduleList
            lessons={slots.filter((lesson) => {
              const lessonDate = new Date(
                lesson.time
              ).toDateString();
              const selectedDate = new Date(
                date
              ).toDateString();
              return lessonDate == selectedDate;
            })}
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
