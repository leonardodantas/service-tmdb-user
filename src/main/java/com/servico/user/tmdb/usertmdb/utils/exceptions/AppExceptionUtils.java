package com.servico.user.tmdb.usertmdb.utils.exceptions;

import com.servico.user.tmdb.usertmdb.models.error.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class AppExceptionUtils {

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<Object> reponseStatusExceptionTratament(ResponseStatusException error){
        ErrorResponse errorResponse = new ErrorResponse(error);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), error.getStatus());
    }

}
