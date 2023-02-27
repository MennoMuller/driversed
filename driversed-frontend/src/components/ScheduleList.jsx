import React from "react";
import LessonItem from "./LessonItem";

function ScheduleList(props) {
  return (
    <div className="flex flex-col">
      {props.lessons.map((lesson) => (
        <LessonItem
          key={lesson.time}
          time={lesson.time}
          person={
            props.instructorPerspective
              ? lesson.student
              : lesson.instructor
          }
        />
      ))}
    </div>
  );
}

export default ScheduleList;
