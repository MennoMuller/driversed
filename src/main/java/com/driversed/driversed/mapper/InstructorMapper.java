package com.driversed.driversed.mapper;

import com.driversed.driversed.dto.PersonGetDto;
import com.driversed.driversed.dto.PersonPostDto;
import com.driversed.driversed.model.Instructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    PersonGetDto toDto(Instructor instructor);

    Instructor toEntity(PersonPostDto instructor);
}
