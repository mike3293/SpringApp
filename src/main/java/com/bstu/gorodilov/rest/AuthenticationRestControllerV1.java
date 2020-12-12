package com.bstu.gorodilov.rest;

import com.bstu.gorodilov.Validator.StudentValidator;
import com.bstu.gorodilov.Validator.TeacherValidator;
import com.bstu.gorodilov.dto.AuthenticationRequestDto;
import com.bstu.gorodilov.exceptions.UserValidationException;
import com.bstu.gorodilov.model.*;
import com.bstu.gorodilov.forms.RegistrationStudentModel;
import com.bstu.gorodilov.forms.RegistrationTeacherModel;
import com.bstu.gorodilov.security.jwt.JwtTokenProvider;
import com.bstu.gorodilov.services.FacultyService;
import com.bstu.gorodilov.services.SubjectService;
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
import java.util.stream.Collectors;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final FacultyService facultyService;
    private final SubjectService subjectService;
    private final StudentValidator studentValidator;
    private final TeacherValidator teacherValidator;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, FacultyService facultyService, SubjectService subjectService, StudentValidator studentValidator, TeacherValidator teacherValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.facultyService = facultyService;
        this.subjectService = subjectService;
        this.studentValidator = studentValidator;
        this.teacherValidator = teacherValidator;
    }
    @RequestMapping(value = "/registerStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> Register(@Valid @RequestBody RegistrationStudentModel userDetails, BindingResult errors) throws MethodArgumentNotValidException {

        studentValidator.validate(userDetails, errors);

        if(errors.hasErrors()){
            throw new UserValidationException(errors);
        }

        User user = userDetails.ToUser();
        userService.register(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registerTeacher", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> RegisterTeacher(@Valid @RequestBody RegistrationTeacherModel teacherDetails, BindingResult errors) throws MethodArgumentNotValidException {

        teacherValidator.validate(teacherDetails, errors);

        if(errors.hasErrors()){
            throw new UserValidationException(errors);
        }

        User user = teacherDetails.ToUser();
        userService.register(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            var authToken = new UsernamePasswordAuthenticationToken(username, requestDto.getPassword());
            authenticationManager.authenticate(authToken);
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = {"/faculties"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Faculty>> facultyList() {
        return new ResponseEntity<>(facultyService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/subjects"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subject>> subjectList() {
        return new ResponseEntity<>(subjectService.findAll()
                                                    .stream()
                                                    .filter(i -> i.getStatus() != Status.DELETED)
                                                    .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = {"/userinfo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);
        Role role = user.getRoles().get(0);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("role", role.getName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
