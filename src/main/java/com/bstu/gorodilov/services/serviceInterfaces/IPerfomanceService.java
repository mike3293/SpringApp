package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.services.PerfomanceService;

import java.util.List;

public interface IPerfomanceService {
    List<PerfomanceService> findAll();
}
