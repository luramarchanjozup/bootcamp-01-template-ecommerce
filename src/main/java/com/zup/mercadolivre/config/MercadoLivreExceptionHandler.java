package com.zup.mercadolivre.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MercadoLivreExceptionHandler {
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDto argumentNotValidException(MethodArgumentNotValidException e) {
        return new ExceptionDto(e.getBindingResult().getFieldError().getField().toString(), 
                    HttpStatus.BAD_REQUEST.toString(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ExceptionDto illegalStateException(IllegalStateException e) {
        return new ExceptionDto("No field information", 
                                HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    
}
