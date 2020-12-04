package com.bstu.gorodilov.services;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.Teacher;
import com.bstu.gorodilov.repositories.ISubjectRepository;
import com.bstu.gorodilov.repositories.ITeacherRepository;
import com.bstu.gorodilov.services.serviceInterfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;

    @Autowired
    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public List<Teacher> findAll() {
        return null;
    }
}
