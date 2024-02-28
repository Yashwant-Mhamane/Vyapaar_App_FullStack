package com.vyapaar.vyapaarservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Product Already Exists")
public class BillAlreadyExistException extends Exception {
    public BillAlreadyExistException(String message) {
        super(message);
    }
}
