package com.bstu.gorodilov.services;

import com.bstu.gorodilov.dto.RateDto;
import com.bstu.gorodilov.mail.MyConstants;
import com.bstu.gorodilov.model.AcademicPerformance;
import com.bstu.gorodilov.model.Status;
import com.bstu.gorodilov.model.Subject;
import com.bstu.gorodilov.model.User;
import com.bstu.gorodilov.repositories.IPerfomanceRepository;
import com.bstu.gorodilov.repositories.ISubjectRepository;
import com.bstu.gorodilov.repositories.IUserRepository;
import com.bstu.gorodilov.services.serviceInterfaces.IPerfomanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PerfomanceService implements IPerfomanceService {
    private final IPerfomanceRepository perfomanceRepository;
    private final ISubjectRepository iSubjectRepository;
    private final IUserRepository iUserRepository;
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    public PerfomanceService(IPerfomanceRepository perfomanceRepository, ISubjectRepository iSubjectRepository, IUserRepository iUserRepository) {
        this.perfomanceRepository = perfomanceRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<PerfomanceService> findAll() {
        return null;
    }

    @Override
    public void rate(RateDto rateDto) {
        AcademicPerformance academicPerformance = new AcademicPerformance();
        academicPerformance.setStatus(Status.ACTIVE);
        academicPerformance.setCreated(new Date());
        academicPerformance.setUpdated(new Date());
        academicPerformance.setDiscription(rateDto.getDescription());
        academicPerformance.setMark(rateDto.getMark());

        User user1 = iUserRepository.findByUsername(rateDto.getUsername());
        Subject subject = iSubjectRepository.findBySubject(rateDto.getSubject()).get(0);

        academicPerformance.setSubject(subject);
        academicPerformance.setUser(user1);
        perfomanceRepository.save(academicPerformance);


        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.TO_EMAIL);
        //message.setTo(user1.getEmail());
        message.setSubject("СДО BSTU");
        message.setText(String.format("Предмет: %s; Оценка: %d; %s",rateDto.getSubject(), rateDto.getMark(), rateDto.getDescription()));

        this.emailSender.send(message);
    }
}
