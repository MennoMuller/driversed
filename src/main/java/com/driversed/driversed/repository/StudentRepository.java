package com.driversed.driversed.repository;

import com.driversed.driversed.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends CrudRepository<Student, Long> {
}
