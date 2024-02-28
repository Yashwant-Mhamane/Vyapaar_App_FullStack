//package com.niit.bej.userservice.repository;
//
//import com.niit.bej.userservice.domain.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////@ExtendWith(SpringExtension.class)
////@DataJpaTest
//class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void findByEmailIdAndPassword() {
//    }
//
//    @Test
//    public void getAllUsers(){
//        List<User> userList = new ArrayList<>();
//        User user = new User("test1@gmail.com", "test1");
//        userList.add(user);
//        userRepository.save(user);
//
//        List<User> userList1 = userRepository.findAll();
//
//        assertEquals(1, userList1.size());
//    }
//}

package com.niit.bej.userservice.repository;

import com.niit.bej.userservice.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getAllUsers(){
        List<User> userList = new ArrayList<>();
        User user = new User("test1@gmail.com", "test1", null);
        userList.add(user);
        userRepository.save(user);

        List<User> userList1 = userRepository.findAll();

        assertEquals(1, userList1.size());
    }
}
