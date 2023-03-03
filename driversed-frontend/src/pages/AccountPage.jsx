import React from "react";
import LoginInput from "../components/LoginInput";

function AccountPage(props) {
  function login(username, password) {
    props.setUser({
      username: username,
      password: password,
      roles: "ROLE_ADMIN,ROLE_STUDENT,ROLE_INSTRUCTOR"
    });
  }
  return (
    <div>
      <h2 className="text-4xl font-bold">Account</h2>
      {props.user.username ? (
        <>
          <h3 className="text-3xl font-bold">
            {props.user.username}
          </h3>
          <br />
          <button
            className="mt-1 border border-solid border-emerald-500 p-1 hover:text-emerald-500"
            onClick={() => props.setUser({})}
          >
            Log Out
          </button>
        </>
      ) : (
        <LoginInput onSubmit={login} />
      )}
    </div>
  );
}

export default AccountPage;
