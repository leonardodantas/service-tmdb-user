package com.servico.user.tmdb.usertmdb.models.responses;

import com.servico.user.tmdb.usertmdb.models.dto.UserDTO;
import com.servico.user.tmdb.usertmdb.models.entity.Movie;
import com.servico.user.tmdb.usertmdb.models.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MoviesUserResponse {

    private String id;
    private String name;
    private List<MovieResponse> movies;

    public MoviesUserResponse() {

    }
    private MoviesUserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.movies = user.getMovies().stream().map(MovieResponse::new).collect(Collectors.toList());
    }

    private MoviesUserResponse(UserDTO userDTO, Movie movie) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.movies = Collections.singletonList(new MovieResponse(movie));
    }

    public static MoviesUserResponse createMovieUserResponseWithOneMovie(UserDTO userDTO, Movie movie){
        return new MoviesUserResponse(userDTO, movie);
    }

    public static MoviesUserResponse createMovieUserResponseWithAllMovies(User user){
        return new MoviesUserResponse(user);
    }
}
