package com.driversed.driversed.service;

import com.driversed.driversed.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;
}
