import React from "react";

function PeopleTable(props) {
  return (
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
        </tr>
      </thead>
      <tbody>
        {props.people.map((person) => (
          <tr key={person.id}>
            <td>{person.id}</td>
            <td>{person.name}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default PeopleTable;
