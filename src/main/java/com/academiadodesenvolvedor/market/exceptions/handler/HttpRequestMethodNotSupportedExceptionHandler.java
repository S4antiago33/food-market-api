package com.academiadodesenvolvedor.market.exceptions.handler;

import com.academiadodesenvolvedor.market.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpRequestMethodNotSupportedExceptionHandler {

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionDTO handler(HttpRequestMethodNotSupportedException exception){
        return new ExceptionDTO("Método não permitido");
    }
}
