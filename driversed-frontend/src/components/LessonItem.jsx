import React from "react";

function LessonItem(props) {
  const date = new Date(props.time);
  return (
    <button
      className={
        (props.person
          ? "bg-blue-400 dark:bg-blue-600"
          : "bg-slate-200 dark:bg-slate-800") +
        " m-1 rounded p-2"
      }
      onClick={() => props.onClick(props.id)}
    >
      {props.showDate ? date.toDateString() + " " : ""}
      {date.toLocaleTimeString()}
      <br />
      {props.person
        ? props.person.name
        : "No student scheduled"}
    </button>
  );
}

export default LessonItem;
