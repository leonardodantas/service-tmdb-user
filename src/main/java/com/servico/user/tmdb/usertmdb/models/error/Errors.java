package com.servico.user.tmdb.usertmdb.models.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Errors {

    private String field;
    private String message;

    public Errors(String message) {
        this.message = message;
    }

    public Errors(String field, String mensagem) {
        this.field = field;
        this.message = mensagem;
    }
}