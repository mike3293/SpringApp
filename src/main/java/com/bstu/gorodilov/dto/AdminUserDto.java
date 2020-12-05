package com.bstu.gorodilov.dto;

import com.bstu.gorodilov.model.Faculty;
import com.bstu.gorodilov.model.Status;
import com.bstu.gorodilov.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String username;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        return adminUserDto;
    }
}
