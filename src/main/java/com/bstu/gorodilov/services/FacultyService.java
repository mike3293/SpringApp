package com.bstu.gorodilov.services;

import com.bstu.gorodilov.model.Faculty;
import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.repositories.IFacultyRepository;
import com.bstu.gorodilov.services.serviceInterfaces.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FacultyService implements IFacultyService {
    private final IFacultyRepository facultyRepository;

    @Autowired
    public FacultyService(IFacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> findAll() {
        return null;
    }
}