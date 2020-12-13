package com.bstu.gorodilov.Validator;


import com.bstu.gorodilov.aop.LogExecutionTime;
import com.bstu.gorodilov.forms.RegistrationTeacherModel;
import com.bstu.gorodilov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TeacherValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationTeacherModel.class.equals(aClass);
    }

    @Override
    @LogExecutionTime
    public void validate(Object o, Errors errors) {
        RegistrationTeacherModel user = (RegistrationTeacherModel) o;

        if(userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "", "This username is already in use");
        }
    }
}
