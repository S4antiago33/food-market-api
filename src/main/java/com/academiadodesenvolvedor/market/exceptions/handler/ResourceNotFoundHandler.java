package com.academiadodesenvolvedor.market.exceptions.handler;

import com.academiadodesenvolvedor.market.DTOs.ExceptionDTO;
import com.academiadodesenvolvedor.market.exceptions.ResourceNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundHandler {
//rirain
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionDTO handler(ResourceNotFoundException exception){
        return new ExceptionDTO(exception.getMessage());
    }
}

