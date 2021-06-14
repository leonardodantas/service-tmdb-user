package com.servico.user.tmdb.usertmdb.models.error;

import lombok.Getter;

@Getter
public class ErrorResponseStatus {

    private String uuid;
    private String message;
    private String date;

}
