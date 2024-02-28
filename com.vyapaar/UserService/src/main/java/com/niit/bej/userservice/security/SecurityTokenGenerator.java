package com.niit.bej.userservice.security;

import com.niit.bej.userservice.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String, String> generateToken(User user);
}
