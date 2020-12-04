package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    List<Student> findByStudentMiddleName(String MiddleName);
}
