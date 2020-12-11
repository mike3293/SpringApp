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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    @Override
    public List<User> getStudents(String facultyName, String subjectName, Integer group, Integer course, String username) {
        Faculty faculty = iFacultyRepository.findByFaculty(facultyName);
        Subject subject = iSubjectRepository.findBySubject(subjectName).get(0);
        User user1 = iUserRepository.findByUsername(username);

        var subjList = iTeacherToGroupRepository.findByUser(user1);
        List<User> students = new ArrayList<>();
        for(var i: subjList){
            if(i.getSubject().getSubject().equals(subject.getSubject())){
                students = iUserRepository.findByUserCourseAndFacultyNameAndUserGroup(course, faculty, group);
                break;
            }
        }
        return students;
    }
}
