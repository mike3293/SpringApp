package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.model.Subject;

import java.util.List;

public interface ISubjectService {
    List<Subject> findAll();
    List<Subject> findBySubjectName(String subject);
    void deleteSubjectByName(String subject);
    void addSubject(String subject);
}