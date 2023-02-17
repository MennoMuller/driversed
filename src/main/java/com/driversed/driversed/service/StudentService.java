package com.driversed.driversed.service;

import com.driversed.driversed.model.Student;
import com.driversed.driversed.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    //CREATE
    public Student newStudent(Student student) {
        return studentRepository.save(student);
    }

    //READ
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
