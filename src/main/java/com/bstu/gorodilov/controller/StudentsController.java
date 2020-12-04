package com.bstu.gorodilov.controller;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.services.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class StudentsController {
    public StudentsController(SubjectService subjectService) {
        SubjectService = subjectService;
    }

    private SubjectService SubjectService;
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

    @GetMapping(value = {"/allalbums"})
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("subjects");
        List<Subject> albums = SubjectService.findAll();
        model.addAttribute("albums", albums);
        log.info("/allalbums was called(GET)");
        return modelAndView;
    }
}