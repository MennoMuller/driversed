package com.driversed.driversed.repository;

import com.driversed.driversed.model.Instructor;
import com.driversed.driversed.model.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public interface LessonRepository extends CrudRepository<Lesson, Long> {
    boolean existsByInstructorAndTime(Instructor instructor, LocalDateTime time);
}
