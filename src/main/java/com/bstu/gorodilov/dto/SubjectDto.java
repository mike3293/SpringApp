package com.bstu.gorodilov.dto;

import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectDto {
    private List<Subject> subjects;

    public static Subject toSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setSubject(subjectDto.getSubjects().get(0).getSubject());
        return subject;
    }

    public static SubjectDto fromUser(User user) {
        SubjectDto subjectDto = new SubjectDto();
        List<Subject> subjects = new ArrayList<>(user.getSubjects());
        subjectDto.setSubject(subjects);
        return subjectDto;
    }

    public void setSubject(List<Subject> subject) {
        this.subjects = subject;
    }
}