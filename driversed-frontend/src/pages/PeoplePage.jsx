import React from "react";
import CreatePersonForm from "../components/CreatePersonForm";
import PeopleTable from "../components/PeopleTable";

function PeoplePage(props) {
  return (
    <div className="flex flex-col">
      <h2 className="text-4xl font-bold">People</h2>
      <div className="flex flex-row justify-around">
        <div className="flex flex-col">
          <h3 className="text-3xl font-bold">Students</h3>
          <PeopleTable people={props.students} />
          <CreatePersonForm
            submitButton="Add Student"
            onSubmit={props.addStudent}
          />
        </div>
        <div className="flex flex-col">
          <h3 className="text-3xl font-bold">
            Instructors
          </h3>
          <PeopleTable people={props.instructors} />
          <CreatePersonForm
            submitButton="Add Instructor"
            onSubmit={props.addInstructor}
          />
        </div>
      </div>
    </div>
  );
}

export default PeoplePage;
