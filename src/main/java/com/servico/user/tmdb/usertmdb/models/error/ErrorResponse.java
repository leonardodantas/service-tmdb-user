package com.servico.user.tmdb.usertmdb.models.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class ErrorResponse {

    private final String uuid;
    private final HttpStatus httpStatus;
    private final LocalDateTime date;
    private final int httpStatusCode;
    private List<Errors> errors;

    public ErrorResponse(ResponseStatusException error) {
        uuid = UUID.randomUUID().toString();
        httpStatus = error.getStatus();
        httpStatusCode = error.getStatus().value();
        date = LocalDateTime.now();
        this.errors = Collections.singletonList(new Errors(error.getReason()));
    }

    public ErrorResponse(MethodArgumentNotValidException exception, List<Errors> errors) {
        this.uuid = UUID.randomUUID().toString();
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
        this.date = LocalDateTime.now();
        this.errors = errors;
    }
}