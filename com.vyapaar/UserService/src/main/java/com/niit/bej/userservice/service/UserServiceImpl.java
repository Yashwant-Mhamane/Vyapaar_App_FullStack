package com.niit.bej.userservice.service;

import com.niit.bej.userservice.domain.User;
import com.niit.bej.userservice.exception.UserAlreadyExistException;
import com.niit.bej.userservice.exception.UserNotFoundException;
import com.niit.bej.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        Optional<User> userDetails = userRepository.findById(user.getEmailId());
        if (userDetails.isPresent()) {
            throw new UserAlreadyExistException("User Already Exist");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public User login(String emailId, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailIdAndPassword(emailId, password);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User Not Found Exception");
        }
    }

    @Override
    public void sendMail(String toEmail,String subject,String body){
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();

        simpleMailMessage.setFrom("todotracker80@gmail.com");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);

        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail sent successfully..");
    }

    @Override
    public String generateOtp() {
        int otp= ThreadLocalRandom.current().nextInt(1000,9999);
        return Integer.toString(otp);
    }

    @Override
//    @EventListener(ApplicationReadyEvent.class)
    public void mailSender(String emailId) throws UserNotFoundException {
        String otp = generateOtp();

       if(userRepository.findById(emailId).isPresent()){
           User user= userRepository.findById(emailId).get();
        user.setOtp(otp);
        userRepository.save(user);
        sendMail(emailId,"OTP","OTP From Vyapaar App "+otp);}
        else {
            throw new UserNotFoundException("User Not Found Exception");
        }
    }

    @Override
    public boolean verifyOtp(String emailId, String otp) {
        User user = userRepository.findById(emailId).get();
        return user != null && user.getOtp().equals(otp);
    }

    @Override
    public User forgetPassword(User user, String password) throws UserNotFoundException {

        if (verifyOtp(user.getEmailId(), user.getOtp())) {
            user.setPassword(password);
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("OTP wrong.");
        }
    }
}
