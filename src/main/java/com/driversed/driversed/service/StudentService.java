package com.driversed.driversed.service;

import com.driversed.driversed.dto.PersonGetDto;
import com.driversed.driversed.dto.PersonPostDto;
import com.driversed.driversed.mapper.StudentMapper;
import com.driversed.driversed.model.Lesson;
import com.driversed.driversed.model.Student;
import com.driversed.driversed.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LessonService lessonService;
    @Autowired
    StudentMapper studentMapper;

    //CREATE
    public void newStudent(PersonPostDto student) {
        Student newStudent = studentMapper.toEntity(student);
        studentRepository.save(newStudent);
    }

    //READ
    public Iterable<PersonGetDto> getAllStudents() {
        ArrayList<PersonGetDto> studentList = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            studentList.add(studentMapper.toDto(student));
        }
        return studentList;
    }

    //UPDATE
    public Student reserveLessonForStudent(long studentId, long lessonId) {
        Optional<Student> foundStudent = studentRepository.findById(studentId);
        if (!foundStudent.isPresent()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student student = foundStudent.get();
        Lesson lesson = lessonService.addStudentToLesson(lessonId, student);
        student.getLessons().add(lesson);
        return studentRepository.save(student);
    }

    //DELETE
    public String deleteStudentById(long id) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        if (!foundStudent.isPresent()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student student = foundStudent.get();
        for (Lesson lesson : student.getLessons()) {
            lessonService.removeStudentFromLesson(lesson.getId());
        }
        studentRepository.deleteById(id);
        return "Successfully deleted student";
    }
}
