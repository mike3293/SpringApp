package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ITeacherRepository extends JpaRepository<Teacher, Integer>  {
}
