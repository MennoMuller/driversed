package com.driversed.driversed.service;

import com.driversed.driversed.model.Instructor;
import com.driversed.driversed.model.Lesson;
import com.driversed.driversed.model.Student;
import com.driversed.driversed.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    //CREATE
    public Lesson newLesson(Instructor instructor, LocalDateTime dateTime) {
        Lesson lesson = new Lesson(dateTime, instructor);
        return lessonRepository.save(lesson);
    }

    //READ
    public Iterable<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    //UPDATE
    public Lesson addStudentToLesson(long lessonId, Student student) {
        Optional<Lesson> foundLesson = lessonRepository.findById(lessonId);
        if (!foundLesson.isPresent()) {
            throw new IllegalArgumentException("Lesson does not exist");
        }
        Lesson lesson = foundLesson.get();
        if (lesson.getStudent() != null) {
            throw new IllegalArgumentException("Lesson already reserved");
        }
        lesson.setStudent(student);
        return lessonRepository.save(lesson);
    }

    public Lesson removeStudentFromLesson(long id) {
        Optional<Lesson> foundLesson = lessonRepository.findById(id);
        if (!foundLesson.isPresent()) {
            throw new IllegalArgumentException("Lesson does not exist");
        }
        Lesson lesson = foundLesson.get();
        lesson.setStudent(null);
        return lessonRepository.save(lesson);
    }


    public boolean existsByInstructorAndTime(Instructor instructor, LocalDateTime dateTime) {
        return lessonRepository.existsByInstructorAndTime(instructor, dateTime);
    }


}
