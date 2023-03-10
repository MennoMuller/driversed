package com.driversed.driversed.controller;

import com.driversed.driversed.dto.LessonGetDto;
import com.driversed.driversed.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;

    //READ
    @GetMapping("/all")
    public Iterable<LessonGetDto> getAllLessons() {
        return lessonService.getAllLessons();
    }

    //UPDATE
    @PutMapping("/studentauth/{id}/cancel/student")
    public void cancelLessonForStudent(@PathVariable(value = "id") long id) {
        lessonService.removeStudentFromLesson(id);
    }
}
