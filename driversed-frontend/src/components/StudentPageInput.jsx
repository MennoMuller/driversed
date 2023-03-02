import React from "react";
import SelectorBox from "./SelectorBox";

function StudentPageInput(props) {
  return (
    <div className="flex flex-col">
      <SelectorBox
        name="student"
        value={props.student}
        setValue={props.setStudent}
        list={props.students}
      />
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
    </div>
  );
}

export default StudentPageInput;
