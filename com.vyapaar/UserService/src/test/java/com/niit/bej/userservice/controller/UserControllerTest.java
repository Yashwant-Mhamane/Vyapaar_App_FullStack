package com.niit.bej.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.bej.userservice.domain.User;
import com.niit.bej.userservice.exception.UserAlreadyExistException;
import com.niit.bej.userservice.exception.UserNotFoundException;
import com.niit.bej.userservice.security.SecurityTokenGenerator;
import com.niit.bej.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

//    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private SecurityTokenGenerator securityTokenGenerator;

    @InjectMocks
    private UserController userController;

    private User user1;

    @BeforeEach
    void setUp() {
        user1 = new User("ak@gmail.com", "ak", null);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    void tearDown() {
        user1 = null;
    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }
        return result;
    }

    @Test
    void registerUserTestForSuccess() throws Exception {
        when(userService.registerUser(user1)).thenReturn(user1);
        mockMvc.perform(
                        post("/userauth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonToString(user1))
                )
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).registerUser(any());
    }

    @Test
    void registerUserTestForFailure() throws Exception {
        when(userService.registerUser(user1)).thenThrow(UserAlreadyExistException.class);
        mockMvc.perform(
                        post("/userauth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonToString(user1))
                )
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).registerUser(any());
    }

    @Test
    void loginTestForSuccess() throws Exception {

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("access_token", "test-token");

        when(userService.login(user1.getEmailId(), user1.getPassword())).thenReturn(user1);
        when(securityTokenGenerator.generateToken(user1)).thenReturn(tokenMap);

        mockMvc.perform(
                post("/userauth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1))
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).login(user1.getEmailId(), user1.getPassword());
    }

    @Test
    void loginTestForFailure() throws Exception {
        when(userService.login(user1.getEmailId(), user1.getPassword())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(
                        post("/userauth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonToString(user1))
                )
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).login(user1.getEmailId(), user1.getPassword());
    }
}