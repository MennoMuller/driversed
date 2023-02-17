package com.driversed.driversed.service;

import com.driversed.driversed.model.Lesson;
import com.driversed.driversed.model.Student;
import com.driversed.driversed.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LessonService lessonService;

    //CREATE
    public Student newStudent(Student student) {
        return studentRepository.save(student);
    }

    //READ
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    //UPDATE
    public Student reserveLessonForStudent(long studentId, long lessonId) {
        Optional<Student> foundStudent = studentRepository.findById(studentId);
        if (foundStudent.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student student = foundStudent.get();
        Lesson lesson = lessonService.addStudentToLesson(lessonId, student);
        student.getLessons().add(lesson);
        return studentRepository.save(student);
    }
}
