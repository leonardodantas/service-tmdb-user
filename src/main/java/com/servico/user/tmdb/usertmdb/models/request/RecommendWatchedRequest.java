package com.servico.user.tmdb.usertmdb.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class RecommendWatchedRequest {

    @NotNull
    @ApiModelProperty(name = "toRecommend" , value = "Recomendação", example = "true")
    private boolean toRecommend;

    @NotNull @Min(value = 0) @Max(value = 10)
    @ApiModelProperty(name = "note" , value = "Avaliação", example = "7.8")
    private double note;

    @NotNull @NotEmpty
    @ApiModelProperty(name = "resume" , value = "Resumo", example = "Filme muito bom")
    private String resume;
}
