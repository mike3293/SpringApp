package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserMiddleName(String MiddleName);
    User findByUserName(String MiddleName);
    <S extends User> S save(S s);
}