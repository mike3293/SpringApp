package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.AcademicPerformance;
import com.bstu.gorodilov.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IPerfomanceRepository  extends JpaRepository<AcademicPerformance, Integer>  {
    <S extends AcademicPerformance> S save(S s);
}
