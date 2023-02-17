package com.driversed.driversed.repository;

import com.driversed.driversed.model.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
}
