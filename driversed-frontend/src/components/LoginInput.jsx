import React, { useState } from "react";

function LoginInput(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  return (
    <form
      onSubmit={(e) => {
        props.onSubmit(username, password);
        e.preventDefault();
      }}
    >
      <label htmlFor="username">Username </label>
      <input
        type="text"
        name="username"
        id="username"
        value={username}
        onChange={(e) => {
          setUsername(e.target.value);
        }}
        required
      />
      <br />
      <label htmlFor="password">Password </label>
      <input
        type="password"
        name="password"
        id="password"
        value={password}
        onChange={(e) => {
          setPassword(e.target.value);
        }}
        required
      />
      <br />
      <input
        type="submit"
        value="Log In"
        className="mt-1 border border-solid border-emerald-500 p-1 hover:text-emerald-500"
      />
    </form>
  );
}

export default LoginInput;
