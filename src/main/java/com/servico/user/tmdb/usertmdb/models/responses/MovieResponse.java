package com.servico.user.tmdb.usertmdb.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.servico.user.tmdb.usertmdb.models.entity.Movie;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {

    private String id;
    private int idMovieTMDB;
    private String name;
    private boolean toRecommend;
    private boolean watched;
    private RatingResponse rating;

    public MovieResponse(Movie movie) {
        this.id = movie.getId();
        this.idMovieTMDB = movie.getIdMovieTMDB();
        this.name = movie.getName();
        this.toRecommend = movie.isToRecommend();
        this.watched = movie.isWatched();
        this.rating = new RatingResponse(movie.getRating());
    }
}
