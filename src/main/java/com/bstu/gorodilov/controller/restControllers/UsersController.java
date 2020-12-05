package com.bstu.gorodilov.controller.restControllers;

import com.bstu.gorodilov.model.User;
import com.bstu.gorodilov.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    private com.bstu.gorodilov.services.UserService userService;

    @GetMapping("/students")
    List<User> getAllStudents() {

        //throw new StudentNotFoundException(2);
        return userService.findAll();
    }
}
