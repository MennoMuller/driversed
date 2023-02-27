import React, { useEffect, useState } from "react";
import ScheduleList from "../components/ScheduleList";

function StudentPage(props) {
  const [student, setStudent] = useState(0);
  const date = new Date();
  date.setHours(1, 0, 0, 0);
  const [lessons, setLessons] = useState([]);

  useEffect(() => {
    if (student != 0) {
      getFutureLessons();
    } else {
      setLessons([]);
    }
  }, [student]);

  function getFutureLessons() {
    fetch(
      `http://localhost:8082/api/student/${student}/schedule`
    )
      .then((response) => response.json())
      .then((data) => setLessons(data));
  }

  return (
    <div>
      <h1 className="text-5xl font-bold">Student</h1>
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
        </div>
        <ScheduleList
          lessons={lessons.filter((lesson) => {
            const lessonDate = Date.parse(lesson.time);
            const currentDate = date.valueOf();
            return lessonDate >= currentDate;
          })}
          instructorPerspective={false}
        />
      </div>
    </div>
  );
}
export default StudentPage;
