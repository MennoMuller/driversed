package com.driversed.driversed.service;

import com.driversed.driversed.dto.LessonGetDto;
import com.driversed.driversed.dto.PersonGetDto;
import com.driversed.driversed.dto.PersonPostDto;
import com.driversed.driversed.mapper.LessonMapper;
import com.driversed.driversed.mapper.StudentMapper;
import com.driversed.driversed.model.Lesson;
import com.driversed.driversed.model.Student;
import com.driversed.driversed.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LessonService lessonService;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    LessonMapper lessonMapper;

    //CREATE
    public void newStudent(PersonPostDto student) {
        Student newStudent = studentMapper.toEntity(student);
        studentRepository.save(newStudent);
    }

    //READ
    public Iterable<PersonGetDto> getAllStudents() {
        return StreamSupport
                .stream(studentRepository.findAll().spliterator(), false)
                .map(student -> studentMapper.toDto(student))
                .collect(Collectors.toList());
    }

    public Iterable<LessonGetDto> getStudentSchedule(long id) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        if (!foundStudent.isPresent()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student student = foundStudent.get();
        return StreamSupport
                .stream(lessonService.getLessonsByStudent(student).spliterator(), false)
                .filter(lesson -> lesson.getTime().isAfter(LocalDateTime.now()))
                .map(lesson -> lessonMapper.toDto(lesson))
                .collect(Collectors.toList());
    }

    //UPDATE
    public void reserveLessonForStudent(long studentId, long lessonId) {
        Optional<Student> foundStudent = studentRepository.findById(studentId);
        if (!foundStudent.isPresent()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student student = foundStudent.get();
        Lesson lesson = lessonService.addStudentToLesson(lessonId, student);
        student.getLessons().add(lesson);
        studentRepository.save(student);
    }

    //DELETE
    public void deleteStudentById(long id) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        if (!foundStudent.isPresent()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student student = foundStudent.get();
        for (Lesson lesson : student.getLessons()) {
            lessonService.removeStudentFromLesson(lesson.getId());
        }
        studentRepository.deleteById(id);
    }


}
