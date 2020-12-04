package com.bstu.gorodilov.services;

import com.bstu.gorodilov.model.Student;
import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.repositories.IStudentRepository;
import com.bstu.gorodilov.services.serviceInterfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;

    @Autowired
    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> findByStudentMiddleName(String MiddleName) {
        return  this.studentRepository.findByStudentMiddleName(MiddleName);
    }
}
