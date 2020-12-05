package com.bstu.gorodilov.rest;

import com.bstu.gorodilov.Validator.StudentValidator;
import com.bstu.gorodilov.Validator.TeacherValidator;
import com.bstu.gorodilov.dto.AdminUserDto;
import com.bstu.gorodilov.dto.AuthenticationRequestDto;
import com.bstu.gorodilov.dto.StudentDto;
import com.bstu.gorodilov.dto.TeacherDto;
import com.bstu.gorodilov.exceptions.UserValidationException;
import com.bstu.gorodilov.forms.RegistrationStudentModel;
import com.bstu.gorodilov.forms.RegistrationTeacherModel;
import com.bstu.gorodilov.model.Faculty;
import com.bstu.gorodilov.model.Role;
import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import com.bstu.gorodilov.security.jwt.JwtTokenProvider;
import com.bstu.gorodilov.services.FacultyService;
import com.bstu.gorodilov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/userinfo")
public class UserInfoRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final FacultyService facultyService;
    private final StudentValidator studentValidator;
    private final TeacherValidator teacherValidator;

    @Autowired
    public UserInfoRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, FacultyService facultyService, StudentValidator studentValidator, TeacherValidator teacherValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.facultyService = facultyService;
        this.studentValidator = studentValidator;
        this.teacherValidator = teacherValidator;
    }

    @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);
        Role role = user.getRoles().get(0);
        if(role.getName().equals("ROLE_STUDENT")) {
            StudentDto studentDto = StudentDto.fromUser(user);
            return new ResponseEntity<>(studentDto, HttpStatus.OK);
        }else if(role.getName().equals("ROLE_TEACHER")){
            TeacherDto teacherDto = TeacherDto.fromUser(user);
            return new ResponseEntity<>(teacherDto, HttpStatus.OK);
        }else{
            AdminUserDto adminUserDto = AdminUserDto.fromUser(user);
            return new ResponseEntity<>(adminUserDto, HttpStatus.OK);
        }
    }
}
