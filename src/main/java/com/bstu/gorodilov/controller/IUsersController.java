package com.bstu.gorodilov.controller;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import com.bstu.gorodilov.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class IUsersController {
    @Autowired
    private com.bstu.gorodilov.services.SubjectService SubjectService;
    @Autowired
    private com.bstu.gorodilov.services.UserService userService;
    private static List<Subject> albums = new ArrayList<>();

    static {
        // albums.add(new Student("Full Stack Development with JHipster", "Deepu K Sasidharan, Sendil Kumar"));
        // albums.add(new Student("Pro Spring Security", "Carlo Scarioni, Massimo Nardone"));
    }

    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    //@GetMapping(value = {"/", "index"})
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called(GET)");
        return modelAndView;
    }

    @GetMapping(value = {"/subjects"})
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("subjects");
        List<User> albums = this.userService.findAll();
        model.addAttribute("albums", albums);
        log.info("/subjects was called(GET)");
        return modelAndView;
    }
}