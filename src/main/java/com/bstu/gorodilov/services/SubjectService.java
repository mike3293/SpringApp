package com.bstu.gorodilov.services;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.repositories.ISubjectRepository;
import com.bstu.gorodilov.services.serviceInterfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectService implements ISubjectService {
    private final ISubjectRepository subjectRepository;

    @Autowired
    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> findAll() {
        return this.subjectRepository.findAll();
    }
}
