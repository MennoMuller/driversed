package com.driversed.driversed.service;

import com.driversed.driversed.model.Instructor;
import com.driversed.driversed.model.Lesson;
import com.driversed.driversed.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
}
