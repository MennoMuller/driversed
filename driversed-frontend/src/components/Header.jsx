import React from "react";
import { NavLink } from "react-router-dom";

function Header(props) {
  return (
    <div className="w-screen bg-red-500">
      Blabla
      <nav>
        <NavLink
          to="/people"
          className="rounded p-2 text-lg text-black hover:opacity-80 dark:text-white"
        >
          People
        </NavLink>
        <NavLink
          to="/instructor"
          className="rounded p-2 text-lg text-black hover:opacity-80 dark:text-white"
        >
          Instructor
        </NavLink>
        <NavLink
          to="/student"
          className="rounded p-2 text-lg text-black hover:opacity-80 dark:text-white"
        >
          Student
        </NavLink>
      </nav>
    </div>
  );
}

export default Header;
