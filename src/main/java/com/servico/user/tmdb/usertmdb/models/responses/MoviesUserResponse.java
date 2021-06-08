package com.servico.user.tmdb.usertmdb.models.responses;

import com.servico.user.tmdb.usertmdb.models.entity.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MoviesUserResponse {

    private String id;
    private String name;
    private List<MovieResponse> movies;

    public MoviesUserResponse() {

    }
    public MoviesUserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.movies = user.getMovies().stream().map(MovieResponse::new).collect(Collectors.toList());
    }

}
