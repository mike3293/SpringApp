package com.bstu.gorodilov.forms;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import lombok.Getter;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Getter
public class RegistrationTeacherModel {

    @NotBlank(message = "Username cannot be empty")
    @Length(min = 8, max = 40, message = "Username length must be between 8 and 40 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 8, max = 16, message = "Password length must be between 8 and 40 characters")
    private String password;

    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @NotBlank(message = "Middle Name cannot be empty")
    private String middleName;

    private List<Subject> subjects;

    public User ToUser(){
        return new User(
                this.getUsername(),
                this.getPassword(),
                this.getFirstName(),
                this.getLastName(),
                this.getMiddleName()
        );
    }
}

