package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.dto.TeacherToGroupDto;
import com.bstu.gorodilov.model.TeacherToGroup;
import com.bstu.gorodilov.model.User;

import java.util.List;

public interface ITeacherToGroupService {
    void addRecord(TeacherToGroupDto teacherToGroupDto);
    List<User> getStudents(String facultyName, String subjectName, Integer groupValue, Integer courseValue, String username);
}
