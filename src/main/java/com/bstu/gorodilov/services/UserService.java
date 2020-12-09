package com.bstu.gorodilov.services;

import com.bstu.gorodilov.dto.TeacherDto;
import com.bstu.gorodilov.model.Role;
import com.bstu.gorodilov.model.Status;
import com.bstu.gorodilov.model.User;
import com.bstu.gorodilov.repositories.IRoleRepository;
import com.bstu.gorodilov.repositories.IUserRepository;
import com.bstu.gorodilov.services.serviceInterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class    UserService implements IUserService {
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserService(IUserRepository userRepository, IRoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(User user) {
        Role roleUser = (user.getFacultyName() == null) ? roleRepository.findByName("ROLE_TEACHER"): roleRepository.findByName("ROLE_STUDENT");

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String mail) {
        return userRepository.findByEmail(mail);
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getTeachers() {
        return userRepository.findAll().stream()
                                               .filter(x -> x.getRoles().get(0).getName().equals("ROLE_TEACHER"))
                                               .collect(Collectors.toList());
    }
}
