package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.Teacher;

import java.util.List;

public interface ITeacherService {
    List<Teacher> findAll();
}
