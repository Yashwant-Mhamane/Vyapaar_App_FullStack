package com.niit.bej.userservice.service;


import com.niit.bej.userservice.domain.User;
import com.niit.bej.userservice.exception.UserAlreadyExistException;
import com.niit.bej.userservice.exception.UserNotFoundException;

public interface UserService {


    User registerUser(User user) throws UserAlreadyExistException;

    User login(String emailId, String password) throws UserNotFoundException;

//    public User updatePassword(String emailId,User user);

    public void sendMail(String toEmail,String subject,String body);

    String generateOtp();

    public boolean verifyOtp(String emailId, String otp);

    public User forgetPassword(User user, String password) throws UserNotFoundException;

    public void mailSender(String emailId) throws UserNotFoundException;

}
