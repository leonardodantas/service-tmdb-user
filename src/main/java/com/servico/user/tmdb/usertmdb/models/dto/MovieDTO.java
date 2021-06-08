package com.servico.user.tmdb.usertmdb.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MovieDTO {

    @JsonProperty(value = "id")
    private int id;
    @JsonProperty(value = "adult")
    private boolean adulto;
    @JsonProperty(value = "backdrop_path")
    private String caminhoDaImagemDeFundo;
    @JsonProperty(value = "genre_ids")
    private List<Integer> idsDosGeneros;
    @JsonProperty(value = "original_language")
    private String idiomaOriginal;
    @JsonProperty(value = "title")
    private String titulo;
    @JsonProperty(value = "original_title")
    private String tituloOriginal;
    @JsonProperty(value = "overview")
    private String resumo;
    @JsonProperty(value = "popularity")
    private double populariedade;
    @JsonProperty(value = "poster_path")
    private String caminhoDoPoster;
    @JsonProperty(value = "release_date")
    private String dataDeLancamento;
    @JsonProperty(value = "vote_average")
    private double nota;
    @JsonProperty(value = "vote_count")
    private int quantidadeDeVotos;
    @JsonProperty(value = "video")
    private boolean videos;

}