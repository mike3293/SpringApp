package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.dto.RateDto;
import com.bstu.gorodilov.model.AcademicPerformance;
import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import com.bstu.gorodilov.services.PerfomanceService;

import java.util.List;

public interface IPerfomanceService {
    void rate(RateDto rateDto);
    List<String> findByUser(String username);
}
