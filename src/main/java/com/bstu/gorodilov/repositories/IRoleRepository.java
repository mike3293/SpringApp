package com.bstu.gorodilov.repositories;

import com.bstu.gorodilov.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
