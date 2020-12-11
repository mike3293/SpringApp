package com.bstu.gorodilov.rest;

import com.bstu.gorodilov.dto.RateDto;
import com.bstu.gorodilov.dto.StudentDto;
import com.bstu.gorodilov.mail.MyConstants;
import com.bstu.gorodilov.services.PerfomanceService;
import com.bstu.gorodilov.services.SubjectService;
import com.bstu.gorodilov.services.TeacherToGroupService;
import com.bstu.gorodilov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/teachers")
public class TeacherRestControllerV1 {
    private final TeacherToGroupService teacherToGroupService;
    private final PerfomanceService perfomanceService;


    @Autowired
    public TeacherRestControllerV1(TeacherToGroupService teacherToGroupService, PerfomanceService perfomanceService) {
        this.teacherToGroupService = teacherToGroupService;
        this.perfomanceService = perfomanceService;
    }

    @GetMapping(value = "/getStudents")
    public ResponseEntity<List<StudentDto>> getStudentByUsername(@RequestParam Map<String, String> mapParam){
        var facultyName = mapParam.get("faculty");
        var subjectName = mapParam.get("subjectName");
        var group = Integer.parseInt(mapParam.get("userCourse"));
        var course = Integer.parseInt(mapParam.get("userGroup"));
        var username = mapParam.get("userName");

        var userList = teacherToGroupService.getStudents(facultyName, subjectName, group, course, username);
        List<StudentDto> studentDtoList = new ArrayList<>();
        if(userList != null){
            for(var i: userList){
                studentDtoList.add(StudentDto.fromUser(i));
            }
        }
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    @PostMapping("/rateStudent")
    public ResponseEntity addTeacherToGroupRecord(@RequestBody RateDto rateDto) {
        perfomanceService.rate(rateDto);

        return new ResponseEntity<>(Objects.requireNonNull(rateDto), HttpStatus.CREATED);
    }
}