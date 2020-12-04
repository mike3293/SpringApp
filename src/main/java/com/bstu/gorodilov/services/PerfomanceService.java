package com.bstu.gorodilov.services;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.repositories.IPerfomanceRepository;
import com.bstu.gorodilov.services.serviceInterfaces.IPerfomanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PerfomanceService implements IPerfomanceService {
    private final IPerfomanceRepository perfomanceRepository;

    @Autowired
    public PerfomanceService(IPerfomanceRepository perfomanceRepository) {
        this.perfomanceRepository = perfomanceRepository;
    }

    @Override
    public List<PerfomanceService> findAll() {
        return null;
    }
}
