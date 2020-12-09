package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.model.Faculty;
import com.bstu.gorodilov.model.Subject;

import java.util.List;

public interface IFacultyService {
    List<Faculty> findAll();
    void addFaculty(String facultyName);
}
