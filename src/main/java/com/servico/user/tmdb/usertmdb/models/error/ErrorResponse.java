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
    private List<Errors> errors;

    public ErrorResponse(ErrorResponseStatus error, HttpStatus httpStatus) {
        this.uuid = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.errors = Collections.singletonList(new Errors(error.getMessage()));
        this.httpStatus = httpStatus;
    }

    public ErrorResponse(List<Errors> dto) {
        this.uuid = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errors = dto;
    }

    public ErrorResponse(HttpStatus status, String message) {
        this.uuid = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.httpStatus = status;
        this.errors = Collections.singletonList(new Errors(message));
    }
}