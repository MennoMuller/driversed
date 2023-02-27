import React, { useEffect, useState } from "react";
import ScheduleList from "../components/ScheduleList";

function InstructorPage(props) {
  const [instructor, setInstructor] = useState(0);
  const [date, setDate] = useState("");
  const [lessons, setLessons] = useState([]);

  useEffect(() => {
    if (instructor != 0) {
      getSchedule();
    }
  }, [instructor]);

  function getSchedule() {
    fetch(
      `http://localhost:8082/api/instructor/${instructor}/schedule`
    )
      .then((response) => response.json())
      .then((data) => setLessons(data));
  }

  function setAvailable() {
    fetch(
      `http://localhost:8082/api/instructor/${instructor}/available`,
      { method: "PUT", body: date }
    ).then(() => getSchedule());
  }

  return (
    <div>
      <h1 className="text-5xl font-bold">Instructor</h1>
      <div className="flex flex-row justify-evenly">
        <div className="flex flex-col">
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
          {date &&
          lessons.filter((lesson) => {
            const lessonDate = new Date(
              lesson.time
            ).toDateString();
            const selectedDate = new Date(
              date
            ).toDateString();
            console.log(date);
            return lessonDate == selectedDate;
          }).length === 0 ? (
            <button onClick={() => setAvailable()}>
              Set as available
            </button>
          ) : (
            <></>
          )}
        </div>
        <ScheduleList
          lessons={lessons.filter((lesson) => {
            const lessonDate = new Date(
              lesson.time
            ).toDateString();
            const selectedDate = new Date(
              date
            ).toDateString();
            console.log(date);
            return lessonDate == selectedDate;
          })}
          instructorPerspective={true}
        />
      </div>
    </div>
  );
}
export default InstructorPage;