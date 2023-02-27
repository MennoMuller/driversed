package com.driversed.driversed.mapper;

import com.driversed.driversed.dto.LessonGetDto;
import com.driversed.driversed.model.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonGetDto toDto(Lesson lesson);
}
