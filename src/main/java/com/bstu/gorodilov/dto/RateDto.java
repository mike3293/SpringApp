package com.bstu.gorodilov.dto;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import lombok.Data;

@Data
public class RateDto {
    private String username;
    private String subject;
    private Integer mark;
    private String description;
}
