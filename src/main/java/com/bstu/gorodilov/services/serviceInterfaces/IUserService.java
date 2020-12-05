package com.bstu.gorodilov.services.serviceInterfaces;

import com.bstu.gorodilov.model.User;

import java.util.List;

public interface IUserService {
    User register(User user);
    List<User> getAll();
    User findByUsername(String userName);
    User findByEmail(String userName);
    User findById(Long id);
    void delete(Long id);

}
