package com.vyapaar.vyapaarservice.repository;

import com.vyapaar.vyapaarservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends MongoRepository<User,String> {

}
