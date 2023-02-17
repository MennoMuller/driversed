package com.driversed.driversed.controller;

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
    public Student newStudent(@RequestBody Student student) {
        return studentService.newStudent(student);
    }

    //READ all Students
    @GetMapping("/all")
    public Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
