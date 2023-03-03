import React from "react";
import { NavLink } from "react-router-dom";

function Header(props) {
  const name = props.user.username;
  return (
    <div className="w-screen bg-slate-600 p-3">
      <h1 className="mb-2 text-5xl font-bold">
        Driver's Ed
      </h1>
      <nav>
        <NavLink
          to="/"
          className="rounded p-2 text-lg text-black hover:opacity-80 dark:text-white"
        >
          Account
        </NavLink>
        <NavLink
          to="/people"
          className="rounded p-2 text-lg text-black hover:opacity-80 dark:text-white"
          hidden={
            !(
              name &&
              props.user.roles.includes("ROLE_ADMIN")
            )
          }
        >
          People
        </NavLink>
        <NavLink
          to="/instructor"
          className="rounded p-2 text-lg text-black hover:opacity-80 dark:text-white"
          hidden={
            !(
              name &&
              props.user.roles.includes("ROLE_INSTRUCTOR")
            )
          }
        >
          Instructor
        </NavLink>
        <NavLink
          to="/student"
          className="rounded p-2 text-lg text-black hover:opacity-80 dark:text-white"
          hidden={
            !(
              name &&
              props.user.roles.includes("ROLE_STUDENT")
            )
          }
        >
          Student
        </NavLink>
      </nav>
    </div>
  );
}

export default Header;
