package com.bstu.gorodilov.dto;

import java.util.List;
import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherDto {
    private String username;
    private String firstName;
    private String lastName;
    private String middleName;
    private SubjectDto subjects;

    public static TeacherDto fromUser(User user) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setUsername(user.getUsername());
        teacherDto.setFirstName(user.getFirstName());
        teacherDto.setLastName(user.getLastName());
        teacherDto.setMiddleName(user.getMiddleName());
        teacherDto.setSubjects(SubjectDto.fromUser(user));
        return teacherDto;
    }
}
