package com.driversed.driversed.controller;

import com.driversed.driversed.dto.LessonGetDto;
import com.driversed.driversed.dto.PersonGetDto;
import com.driversed.driversed.dto.PersonPostDto;
import com.driversed.driversed.model.Student;
import com.driversed.driversed.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    //CREATE new Student
    @PostMapping("/new")
    public void newStudent(@RequestBody PersonPostDto student) {
        studentService.newStudent(student);
    }

    //READ all Students
    @GetMapping("/all")
    public Iterable<PersonGetDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    //READ a Student's schedule
    @GetMapping("/{id}/schedule")
    public Iterable<LessonGetDto> getStudentSchedule(@PathVariable(value = "id") long id) {
        return studentService.getStudentSchedule(id);
    }

    //UPDATE a Student by reserving a Lesson
    @PutMapping("/{studentId}/reserve/{lessonId}")
    public Student reserveLessonForStudent(@PathVariable(value = "studentId") long studentId, @PathVariable(value = "lessonId") long lessonId) {
        return studentService.reserveLessonForStudent(studentId, lessonId);
    }

    //DELETE a Student by Id
    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable(value = "id") long id) {
        return studentService.deleteStudentById(id);
    }
}
