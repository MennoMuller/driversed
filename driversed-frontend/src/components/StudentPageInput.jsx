import React from "react";

function StudentPageInput(props) {
  return (
    <div className="flex flex-col">
      <select
        name="student"
        id="student"
        value={props.student}
        onChange={(e) => {
          props.setStudent(e.target.value);
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
    </div>
  );
}

export default StudentPageInput;
