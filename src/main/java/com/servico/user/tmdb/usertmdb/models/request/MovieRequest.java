package com.servico.user.tmdb.usertmdb.models.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MovieRequest {

    @NotNull
    private String idMovie;
}
