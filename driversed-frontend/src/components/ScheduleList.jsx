import React from "react";
import LessonItem from "./LessonItem";

function ScheduleList(props) {
  return (
    <div className="flex flex-col">
      {props.lessons.map((lesson) => (
        <LessonItem
          key={lesson.id}
          id={lesson.id}
          time={lesson.time}
          person={
            props.instructorPerspective
              ? lesson.student
              : lesson.instructor
          }
          showDate={!props.instructorPerspective}
          onClick={props.onClick}
        />
      ))}
    </div>
  );
}

export default ScheduleList;
