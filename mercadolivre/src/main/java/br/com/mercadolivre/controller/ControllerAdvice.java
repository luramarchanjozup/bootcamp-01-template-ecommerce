package br.com.mercadolivre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mercadolivre.dto.retorno.FieldErrorRetornoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @Autowired
    private MessageSource messageSource;
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException exception) {	
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        
        List<FieldErrorRetornoDTO> listaFieldErros = new ArrayList<>();
        
        fieldErrors.forEach(error -> {
    		String errorMessage = getErrorMessage(error);
    		FieldErrorRetornoDTO fieldretorno = new FieldErrorRetornoDTO(error.getField(), errorMessage);
    		listaFieldErros.add(fieldretorno);
    	});
        
        Map<List<ObjectError>,List<FieldErrorRetornoDTO>> listaErros = Map.of(globalErrors,listaFieldErros);

        return new ResponseEntity<>(listaErros,HttpStatus.BAD_REQUEST);
    }
    

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}