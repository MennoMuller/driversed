package com.driversed.driversed.repository;

import com.driversed.driversed.model.Instructor;
import com.driversed.driversed.model.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
    boolean existsByInstructorAndTime(Instructor instructor, LocalDateTime time);

    List<Lesson> findByInstructor(Instructor instructor);
}
