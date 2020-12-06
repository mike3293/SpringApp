package com.bstu.gorodilov.services;

import com.bstu.gorodilov.model.Status;
import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.repositories.ISubjectRepository;
import com.bstu.gorodilov.services.serviceInterfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public List<Subject> findBySubjectName(String subject) {
        return this.subjectRepository.findBySubject(subject);
    }

    @Override
    public void deleteSubjectByName(String subject) {
        Subject deSubject = this.subjectRepository.findBySubject(subject).get(0);
        deSubject.setStatus(Status.DELETED);
        this.subjectRepository.save(deSubject);
    }

    @Override
    public void addSubject(String subject) {
        Subject subject1 = new Subject();
        subject1.setStatus(Status.ACTIVE);
        subject1.setCreated(new Date());
        subject1.setUpdated(new Date());
        subject1.setSubject(subject);
        this.subjectRepository.save(subject1);
    }

}
