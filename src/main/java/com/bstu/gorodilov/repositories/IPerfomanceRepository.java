package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.AcademicPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IPerfomanceRepository  extends JpaRepository<AcademicPerformance, Integer>  {
}
