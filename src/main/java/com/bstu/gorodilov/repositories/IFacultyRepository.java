package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IFacultyRepository  extends JpaRepository<Faculty, Integer>  {
}
