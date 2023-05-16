package com.SpringProject.SpringBootProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.rmi.server.UID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends HttpClientErrorException {
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
