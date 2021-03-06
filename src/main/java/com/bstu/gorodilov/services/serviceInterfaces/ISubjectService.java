package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.model.Subject;

import java.util.List;

public interface ISubjectService {
    List<Subject> findAll();
    void deleteSubjectByName(String subject);
    void addSubject(String subject);
}