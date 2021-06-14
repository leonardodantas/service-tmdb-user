package com.servico.user.tmdb.usertmdb.utils.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servico.user.tmdb.usertmdb.models.error.ErrorResponse;
import com.servico.user.tmdb.usertmdb.models.error.ErrorResponseStatus;
import com.servico.user.tmdb.usertmdb.models.error.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionUtils {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<Object> reponseStatusExceptionTratament(ResponseStatusException error) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse;
        try {
            ErrorResponseStatus errorResponseStatus;
            errorResponseStatus = mapper.readValue(error.getReason(), ErrorResponseStatus.class);
            errorResponse = new ErrorResponse(errorResponseStatus, error.getStatus());
        } catch (JsonProcessingException exception) {
            errorResponse = new ErrorResponse(error.getStatus(), error.getReason());
        }
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> reponseStatusExceptionTratament(MethodArgumentNotValidException error) throws JsonProcessingException {
        List<Errors> dto = new ArrayList<>();

        List<FieldError> fieldErrors = error.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            Errors erro = new Errors(e.getField(), mensagem);
            dto.add(erro);
        });

        ErrorResponse errorResponse = new ErrorResponse(dto);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
