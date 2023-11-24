package com.academiadodesenvolvedor.market.exceptions.handler;

import com.academiadodesenvolvedor.market.DTOs.InputErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class InputValidateErrorHandler {
    private MessageSource messageSource;

    @Autowired
    public InputValidateErrorHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<InputErrorDTO> handler(MethodArgumentNotValidException exception){
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        return errors.stream()
                .map(fieldError -> {
                    String message = this.messageSource
                            .getMessage(fieldError, LocaleContextHolder.getLocale());
                    return new InputErrorDTO(fieldError.getField(), message);

                })
                .toList()
                ;

    }
}
