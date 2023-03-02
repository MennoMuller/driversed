import React from "react";
import SelectorBox from "./SelectorBox";

function InstructorPageInput(props) {
  return (
    <div className="flex flex-col">
      <SelectorBox
        name="instructor"
        value={props.instructor}
        setValue={props.setInstructor}
        list={props.instructors}
      />
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
