import React from "react";

function InstructorPageInput(props) {
  return (
    <div className="flex flex-col">
      <select
        name="instructor"
        id="instructor"
        value={props.instructor}
        onChange={(e) => {
          props.setInstructor(e.target.value);
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
        value={props.date}
        onChange={(e) => {
          props.setDate(e.target.value);
        }}
      />
      {props.date &&
      props.lessons.filter((lesson) => {
        const lessonDate = new Date(
          lesson.time
        ).toDateString();
        const selectedDate = new Date(
          props.date
        ).toDateString();
        return lessonDate == selectedDate;
      }).length === 0 ? (
        <button onClick={() => props.setAvailable()}>
          Set as available
        </button>
      ) : (
        <></>
      )}
    </div>
  );
}

export default InstructorPageInput;
