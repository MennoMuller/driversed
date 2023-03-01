package com.driversed.driversed.controller;

import com.driversed.driversed.dto.LessonGetDto;
import com.driversed.driversed.dto.PersonGetDto;
import com.driversed.driversed.dto.PersonPostDto;
import com.driversed.driversed.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/api/instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    //CREATE new Instructor
    @PostMapping("/new")
    public void newInstructor(@RequestBody PersonPostDto instructor) {
        instructorService.newInstructor(instructor);
    }

    //READ all Instructors
    @GetMapping("/all")
    public Iterable<PersonGetDto> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    //READ an Instructor's schedule
    @GetMapping("/{id}/schedule")
    public Iterable<LessonGetDto> getInstructorSchedule(@PathVariable(value = "id") long id) {
        return instructorService.getInstructorSchedule(id);
    }

    //READ an Instructor's available lesson slots
    @GetMapping("/{id}/slots")
    public Iterable<LessonGetDto> getInstructorSlots(@PathVariable(value = "id") long id) {
        return instructorService.getInstructorSlots(id);
    }


    //UPDATE an Instructor by adding a day's worth of lessons
    @PutMapping("/{id}/available")
    public void setInstructorAvailable(@PathVariable(value = "id") long id, @RequestBody String date) {
        instructorService.setInstructorAvailable(id, LocalDate.parse(date));
    }
}
