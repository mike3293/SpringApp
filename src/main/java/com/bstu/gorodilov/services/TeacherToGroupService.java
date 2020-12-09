package com.bstu.gorodilov.services;

import com.bstu.gorodilov.dto.TeacherToGroupDto;
import com.bstu.gorodilov.model.*;
import com.bstu.gorodilov.repositories.IFacultyRepository;
import com.bstu.gorodilov.repositories.ISubjectRepository;
import com.bstu.gorodilov.repositories.ITeacherToGroupRepository;
import com.bstu.gorodilov.repositories.IUserRepository;
import com.bstu.gorodilov.services.serviceInterfaces.ITeacherToGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TeacherToGroupService implements ITeacherToGroupService {
    private final ITeacherToGroupRepository iTeacherToGroupRepository;
    private final IFacultyRepository iFacultyRepository;
    private final ISubjectRepository iSubjectRepository;
    private final IUserRepository iUserRepository;

    @Autowired
    public TeacherToGroupService(ITeacherToGroupRepository iTeacherToGroupRepository, IFacultyRepository iFacultyRepository, ISubjectRepository iSubjectRepository, IUserRepository iUserRepository) {
        this.iTeacherToGroupRepository = iTeacherToGroupRepository;
        this.iFacultyRepository = iFacultyRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iUserRepository = iUserRepository;
    }


    @Override
    public void addRecord(TeacherToGroupDto teacherToGroupDto) {
        Faculty faculty = iFacultyRepository.findByFaculty(teacherToGroupDto.getFaculty());
        Subject subject = iSubjectRepository.findBySubject(teacherToGroupDto.getSubject()).get(0);
        Integer group = teacherToGroupDto.getTgroup();
        Integer course = teacherToGroupDto.getCourse();
        User user1 = iUserRepository.findByUsername(teacherToGroupDto.getUser());
        TeacherToGroup teacherToGroup = new TeacherToGroup(faculty, course, group, user1, subject);
        teacherToGroup.setStatus(Status.ACTIVE);
        teacherToGroup.setCreated(new Date());
        teacherToGroup.setUpdated(new Date());
        iTeacherToGroupRepository.save(teacherToGroup);
    }
}
