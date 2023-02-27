package com.driversed.driversed.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LessonGetDto {
    private Long id;
    private LocalDateTime time;
    private PersonGetDto instructor;
    private PersonGetDto student;
}
