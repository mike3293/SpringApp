package com.bstu.gorodilov.rest;

import com.bstu.gorodilov.dto.AdminUserDto;
import com.bstu.gorodilov.dto.TeacherDto;
import com.bstu.gorodilov.dto.TeacherToGroupDto;
import com.bstu.gorodilov.model.Faculty;
import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import com.bstu.gorodilov.services.FacultyService;
import com.bstu.gorodilov.services.SubjectService;
import com.bstu.gorodilov.services.TeacherToGroupService;
import com.bstu.gorodilov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;
    private final SubjectService subjectService;
    private final FacultyService facultyService;
    private final TeacherToGroupService teacherToGroupService;

    @Autowired
    public AdminRestControllerV1(UserService userService, SubjectService subjectService, FacultyService facultyService, TeacherToGroupService teacherToGroupService) {
        this.userService = userService;
        this.subjectService = subjectService;
        this.facultyService = facultyService;
        this.teacherToGroupService = teacherToGroupService;
    }

    @GetMapping(value = "users/{username}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = {"teachers"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeacherDto>> facultyList() {
        List<TeacherDto> teacherDto = new ArrayList<>();
        for (var i:userService.getTeachers()){
            teacherDto.add(TeacherDto.fromUser(i));
        }
        return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }

    @PostMapping("addSubject")
    public ResponseEntity addSubject(RequestEntity<Subject> subject) {
        subjectService.addSubject(Objects.requireNonNull(subject.getBody()).getSubject());
        return new ResponseEntity<>(Objects.requireNonNull(subject.getBody()).getSubject(), HttpStatus.CREATED);
    }

    @PostMapping("addFaculty")
    public ResponseEntity addFaculty(RequestEntity<Faculty> faculty) {

        facultyService.addFaculty(Objects.requireNonNull(faculty.getBody()).getFaculty());
        return new ResponseEntity<>(Objects.requireNonNull(faculty.getBody()).getFaculty(), HttpStatus.CREATED);
    }

    @PutMapping("deActivateSubject")
    public ResponseEntity deActivateSubject(RequestEntity<Subject> subject) {
        subjectService.deleteSubjectByName(Objects.requireNonNull(subject.getBody()).getSubject());
        return new ResponseEntity<>(Objects.requireNonNull(subject.getBody()).getSubject(), HttpStatus.CREATED);
    }

    @PostMapping("addTeacherToGroupRecord")
    public ResponseEntity addTeacherToGroupRecord(RequestEntity<TeacherToGroupDto> teacherDtoRequestEntity) {
        teacherToGroupService.addRecord(Objects.requireNonNull(teacherDtoRequestEntity.getBody()));
        return new ResponseEntity<>(Objects.requireNonNull(teacherDtoRequestEntity.getBody()).getSubject(), HttpStatus.CREATED);
    }
}
