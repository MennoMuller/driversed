import { useEffect, useState } from "react";
import "./App.css";
import Header from "./components/Header";
import { Route, Routes } from "react-router-dom";
import PeoplePage from "./pages/PeoplePage";
import InstructorPage from "./pages/InstructorPage";
import StudentPage from "./pages/StudentPage";
import AccountPage from "./pages/AccountPage";

function App() {
  const [students, setStudents] = useState([]);
  const [instructors, setInstructors] = useState([]);
  const [user, setUser] = useState({});

  const getStudents = () => {
    fetch(
      "http://localhost:8082/api/student/studentauth/all",
      {
        headers: {
          Authorization:
            "Basic " +
            btoa(user.username + ":" + user.password)
        }
      }
    )
      .then((response) => response.json())
      .then((data) => setStudents(data));
  };

  const getInstructors = () => {
    fetch(
      "http://localhost:8082/api/instructor/studentauth/all",
      {
        headers: {
          Authorization:
            "Basic " +
            btoa(user.username + ":" + user.password)
        }
      }
    )
      .then((response) => response.json())
      .then((data) => setInstructors(data));
  };

  const addStudent = (name) => {
    const newStudent = JSON.stringify({
      name: name
    });
    fetch("http://localhost:8082/api/student/admin/new", {
      method: "POST",
      headers: {
        Authorization:
          "Basic " +
          btoa(user.username + ":" + user.password),
        "Content-Type": "application/json"
      },
      body: newStudent
    }).then(() => getStudents());
  };

  const addInstructor = (name) => {
    const newInstructor = JSON.stringify({
      name: name
    });
    fetch(
      "http://localhost:8082/api/instructor/admin/new",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization:
            "Basic " +
            btoa(user.username + ":" + user.password)
        },
        body: newInstructor
      }
    ).then(() => getInstructors());
  };

  useEffect(() => {
    if (user.username) {
      setStudents([]);
      setInstructors([]);
      getStudents();
      getInstructors();
    }
  }, [user]);

  return (
    <div>
      <header>
        <Header user={user} />
      </header>
      <main>
        <Routes>
          <Route
            path="/people"
            element={
              <PeoplePage
                students={students}
                addStudent={addStudent}
                instructors={instructors}
                addInstructor={addInstructor}
                user={user}
              />
            }
          />
          <Route
            path="/instructor"
            element={
              <InstructorPage
                instructors={instructors}
                user={user}
              />
            }
          />
          <Route
            path="/student"
            element={
              <StudentPage
                user={user}
                students={students}
                instructors={instructors}
              />
            }
          />
          <Route
            path="/account"
            element={
              <AccountPage user={user} setUser={setUser} />
            }
          />
        </Routes>
      </main>
    </div>
  );
}

export default App;
