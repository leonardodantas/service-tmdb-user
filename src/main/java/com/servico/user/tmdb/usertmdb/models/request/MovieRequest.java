package com.servico.user.tmdb.usertmdb.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MovieRequest {

    @NotNull
    @ApiModelProperty(name = "movieId" , value = "ID do filme", example = "460465")
    private String movieId;
}
