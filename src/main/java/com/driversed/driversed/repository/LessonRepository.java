package com.driversed.driversed.repository;

import com.driversed.driversed.model.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
