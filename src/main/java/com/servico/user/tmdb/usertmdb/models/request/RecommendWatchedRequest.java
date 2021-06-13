package com.servico.user.tmdb.usertmdb.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
public class RecommendWatchedRequest {

    @NotNull
    @ApiModelProperty(name = "toRecommend" , value = "Recomendação", example = "true")
    private boolean toRecommend;

    @NotNull
    @ApiModelProperty(name = "note" , value = "Avaliação", example = "7.8")
    private double note;

    @NotNull
    @ApiModelProperty(name = "resume" , value = "Resumo", example = "Filme muito bom")
    private String resume;
}
