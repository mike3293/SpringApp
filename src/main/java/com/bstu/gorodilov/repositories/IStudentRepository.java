package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IStudentRepository  extends JpaRepository<Student, Integer> {
    List<Student> findByStudentMiddleName(String MiddleName);
}
