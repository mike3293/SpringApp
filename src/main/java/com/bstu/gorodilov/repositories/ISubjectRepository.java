package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findBySubject(String subject);
    <S extends Subject> S save(S s);
}
