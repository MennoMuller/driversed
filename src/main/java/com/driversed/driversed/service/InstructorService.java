package com.driversed.driversed.service;

import com.driversed.driversed.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
}
