import React, { useState } from "react";

function CreatePersonForm(props) {
  const [name, setName] = useState("");
  return (
    <form
      onSubmit={(e) => {
        props.onSubmit(name);
        e.preventDefault();
      }}
    >
      <label htmlFor="name">Name</label>
      <input
        type="text"
        name="name"
        id="name"
        value={name}
        onChange={(e) => {
          setName(e.target.value);
        }}
      />
      <br />
      <input type="submit" value={props.submitButton} />
    </form>
  );
}

export default CreatePersonForm;
