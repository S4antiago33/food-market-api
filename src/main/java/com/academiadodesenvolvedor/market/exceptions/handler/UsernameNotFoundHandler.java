package com.academiadodesenvolvedor.market.exceptions.handler;

import com.academiadodesenvolvedor.market.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsernameNotFoundHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ExceptionDTO handler(UsernameNotFoundException error){
        return new ExceptionDTO(error.getMessage());
    }
}
