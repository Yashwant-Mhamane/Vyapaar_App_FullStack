package com.vyapaar.vyapaarservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product Not Found")
public class BillNotFoundException extends Exception {

    public BillNotFoundException(String message) {
        super(message);
    }
}