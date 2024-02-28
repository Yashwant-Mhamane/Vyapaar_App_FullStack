package com.vyapaar.vyapaarservice;

import com.vyapaar.vyapaarservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "eurekaVyapaarAuthClient", url = "eurekaVyapaarAuthClient:8081/")
public interface Proxy {

    @RequestMapping("/userauth/register")
    ResponseEntity<?> registerUser(@RequestBody User user);
}
