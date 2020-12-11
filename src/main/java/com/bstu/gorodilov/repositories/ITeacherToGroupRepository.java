package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.TeacherToGroup;
import com.bstu.gorodilov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeacherToGroupRepository extends JpaRepository<TeacherToGroup, Long> {
    <S extends TeacherToGroup> S save(S s);
    List<TeacherToGroup> findByUser(User user);
}