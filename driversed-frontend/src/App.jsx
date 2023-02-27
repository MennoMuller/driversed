import { useEffect, useState } from "react";
import "./App.css";
import Header from "./components/Header";
import { Route, Routes } from "react-router-dom";
import PeoplePage from "./pages/PeoplePage";
import InstructorPage from "./pages/InstructorPage";
import StudentPage from "./pages/StudentPage";

function App() {
  const [students, setStudents] = useState([]);
  const [instructors, setInstructors] = useState([]);

  const getStudents = () => {
    fetch("http://localhost:8082/api/student/all")
      .then((response) => response.json())
      .then((data) => setStudents(data));
  };

  const getInstructors = () => {
    fetch("http://localhost:8082/api/instructor/all")
      .then((response) => response.json())
      .then((data) => setInstructors(data));
  };

  const addStudent = (name) => {
    const newStudent = JSON.stringify({
      name: name
    });
    fetch("http://localhost:8082/api/student/new", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: newStudent
    }).then(() => getStudents());
  };

  const addInstructor = (name) => {
    const newInstructor = JSON.stringify({
      name: name
    });
    fetch("http://localhost:8082/api/instructor/new", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: newInstructor
    }).then(() => getInstructors());
  };

  useEffect(() => {
    getStudents();
    getInstructors();
  }, []);

  return (
    <div>
      <header>
        <Header />
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
              />
            }
          />
          <Route
            path="/instructor"
            element={
              <InstructorPage instructors={instructors} />
            }
          />
          <Route
            path="/student"
            element={<StudentPage students={students} />}
          />
        </Routes>
      </main>
    </div>
  );
}

export default App;
