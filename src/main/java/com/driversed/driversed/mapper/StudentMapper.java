package com.driversed.driversed.mapper;

import com.driversed.driversed.dto.PersonGetDto;
import com.driversed.driversed.dto.PersonPostDto;
import com.driversed.driversed.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    PersonGetDto toDto(Student student);

    Student toEntity(PersonPostDto student);
}
