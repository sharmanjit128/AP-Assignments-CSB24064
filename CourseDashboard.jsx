import { useState } from "react";
import "./App.css";


function App() {


  const [studentsMap, setStudentsMap] = useState(new Map());
  const [filterCourse, setFilterCourse] = useState("");


  // Add Student
  const addStudent = () => {


    const newStudent = {
      id: Date.now(),
      name: "Student " + Math.floor(Math.random() * 100),
      enrolledCourses: new Set(["Math", "Physics", "Chemistry"]),
      sgpa: (5 + Math.random() * 5).toFixed(2) // SGPA between 5 and 10
    };


    setStudentsMap((prev) => {
      const newMap = new Map(prev);
      newMap.set(newStudent.id, newStudent);
      return newMap;
    });
  };


  // Remove Student
  const removeStudent = (id) => {
    setStudentsMap((prev) => {
      const newMap = new Map(prev);
      newMap.delete(id);
      return newMap;
    });
  };


  // Convert Map → Array
  const studentsArray = [...studentsMap.values()];


  // Sort students by SGPA descending
  const sortedStudents = [...studentsArray].sort(
    (a, b) => b.sgpa - a.sgpa
  );


  // Get all unique courses
  const uniqueCourses = studentsArray.reduce((acc, student) => {
    student.enrolledCourses.forEach((course) => acc.add(course));
    return acc;
  }, new Set());


  // Filter students by selected course
  const filteredStudents = filterCourse
    ? sortedStudents.filter((student) =>
        student.enrolledCourses.has(filterCourse)
      )
    : sortedStudents;


  return (
    <div className="container">
      <h1>Course Enrollment Dashboard</h1>


      <button onClick={addStudent}>Add Student</button>


      <br /><br />


      <input
        type="text"
        placeholder="Filter by course"
        value={filterCourse}
        onChange={(e) => setFilterCourse(e.target.value)}
      />


      <h3>Unique Courses</h3>
      <ul className="course-list">
        {[...uniqueCourses].map((course, index) => (
          <li key={index}>{course}</li>
        ))}
      </ul>


      <h3>Students</h3>


      {filteredStudents.map((student) => (
        <div key={student.id} className="student-card">
          <h4>{student.name}</h4>


          <p>SGPA: {student.sgpa}</p>


          <p>
            Courses:
            {[...student.enrolledCourses].map((course, i) => (
              <span key={i} className="course-tag">
                {course}
              </span>
            ))}
          </p>


          <button
            className="remove-btn"
            onClick={() => removeStudent(student.id)}
          >
            Remove
          </button>
        </div>
      ))}
    </div>
  );
}


export default App;
