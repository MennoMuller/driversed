package com.driversed.driversed.controller;

import com.driversed.driversed.model.Instructor;
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
    public Instructor newInstructor(@RequestBody Instructor instructor) {
        return instructorService.newInstructor(instructor);
    }

    //READ all Instructors
    @GetMapping("/all")
    public Iterable<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    //UPDATE an Instructor by adding a day's worth of lessons
    @PutMapping("/{id}/available")
    public Instructor setInstructorAvailable(@PathVariable(value = "id") long id, @RequestBody String date) {
        return instructorService.setInstructorAvailable(id, LocalDate.parse(date));
    }
}
