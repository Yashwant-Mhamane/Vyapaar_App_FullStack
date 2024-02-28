package com.niit.bej.userservice.repository;

import com.niit.bej.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

      User findByEmailIdAndPassword(String emailId, String password);

}
