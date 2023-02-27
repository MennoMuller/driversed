package com.driversed.driversed.controller;

import com.driversed.driversed.model.Lesson;
import com.driversed.driversed.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;

    //READ
    @GetMapping("/all")
    public Iterable<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }
}
