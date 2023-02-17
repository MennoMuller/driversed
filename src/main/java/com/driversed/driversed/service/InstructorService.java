package com.driversed.driversed.service;

import com.driversed.driversed.model.Instructor;
import com.driversed.driversed.model.Lesson;
import com.driversed.driversed.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    LessonService lessonService;

    //CREATE
    public Instructor newInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    //READ
    public Iterable<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    //UPDATE
    public Instructor setInstructorAvailable(long id, LocalDate date) {
        Optional<Instructor> foundInstructor = instructorRepository.findById(id);
        if (foundInstructor.isEmpty()) {
            return null;
        }
        Instructor instructor = foundInstructor.get();
        for (int i = 9; i < 17; i++) {
            if (i != 12) {
                instructor = addLessonToInstructor(instructor, LocalDateTime.of(date, LocalTime.of(i, 0)));
            }
        }
        return instructorRepository.save(instructor);
    }

    public Instructor addLessonToInstructor(Instructor instructor, LocalDateTime dateTime) {
        Lesson tempLesson = lessonService.newLesson(instructor, dateTime);
        instructor.getLessons().add(tempLesson);
        return instructorRepository.save(instructor);
    }
}
