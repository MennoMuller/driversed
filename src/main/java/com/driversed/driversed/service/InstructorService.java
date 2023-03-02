package com.driversed.driversed.service;

import com.driversed.driversed.dto.LessonGetDto;
import com.driversed.driversed.dto.PersonGetDto;
import com.driversed.driversed.dto.PersonPostDto;
import com.driversed.driversed.mapper.InstructorMapper;
import com.driversed.driversed.mapper.LessonMapper;
import com.driversed.driversed.model.Instructor;
import com.driversed.driversed.model.Lesson;
import com.driversed.driversed.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    LessonService lessonService;
    @Autowired
    InstructorMapper instructorMapper;
    @Autowired
    LessonMapper lessonMapper;

    //CREATE
    public void newInstructor(PersonPostDto instructor) {
        Instructor newInstructor = instructorMapper.toEntity(instructor);
        instructorRepository.save(newInstructor);
    }

    //READ
    public Iterable<PersonGetDto> getAllInstructors() {
        return StreamSupport
                .stream(instructorRepository.findAll().spliterator(), false)
                .map(instructor -> instructorMapper.toDto(instructor))
                .collect(Collectors.toList());
    }

    public Iterable<LessonGetDto> getInstructorSchedule(long id) {
        Optional<Instructor> foundInstructor = instructorRepository.findById(id);
        if (!foundInstructor.isPresent()) {
            throw new IllegalArgumentException("Instructor does not exist");
        }
        Instructor instructor = foundInstructor.get();
        return StreamSupport
                .stream(lessonService.getLessonsByInstructor(instructor).spliterator(), false)
                .map(lesson -> lessonMapper.toDto(lesson))
                .collect(Collectors.toList());
    }

    public Iterable<LessonGetDto> getInstructorSlots(long id) {
        Optional<Instructor> foundInstructor = instructorRepository.findById(id);
        if (!foundInstructor.isPresent()) {
            throw new IllegalArgumentException("Instructor does not exist");
        }
        Instructor instructor = foundInstructor.get();
        return StreamSupport
                .stream(lessonService.getLessonsByInstructor(instructor).spliterator(), false)
                .filter(lesson -> lesson.getStudent() == null && lesson.getTime().isAfter(LocalDateTime.now()))
                .map(lesson -> lessonMapper.toDto(lesson))
                .collect(Collectors.toList());
    }

    //UPDATE
    public void setInstructorAvailable(long id, LocalDate date) {
        Optional<Instructor> foundInstructor = instructorRepository.findById(id);
        if (!foundInstructor.isPresent()) {
            throw new IllegalArgumentException("Instructor does not exist");
        }
        Instructor instructor = foundInstructor.get();
        for (int i = 9; i < 17; i++) {
            if (i != 12) {
                instructor = addLessonToInstructor(instructor, LocalDateTime.of(date, LocalTime.of(i, 0)));
            }
        }
        instructorRepository.save(instructor);
    }

    public Instructor addLessonToInstructor(Instructor instructor, LocalDateTime dateTime) {
        if (lessonService.existsByInstructorAndTime(instructor, dateTime)) {
            throw new IllegalArgumentException("Day is already set available");
        }
        Lesson tempLesson = lessonService.newLesson(instructor, dateTime);
        instructor.getLessons().add(tempLesson);
        return instructorRepository.save(instructor);
    }


}
