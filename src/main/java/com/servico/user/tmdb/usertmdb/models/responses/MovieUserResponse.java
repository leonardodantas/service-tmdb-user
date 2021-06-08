package com.servico.user.tmdb.usertmdb.models.responses;

import com.servico.user.tmdb.usertmdb.models.dto.MovieDTO;
import com.servico.user.tmdb.usertmdb.models.dto.UserDTO;
import com.servico.user.tmdb.usertmdb.models.entity.User;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class MovieUserResponse {

    private UserDTO user;
    private List<MovieDTO> movies;

    public MovieUserResponse(UserDTO userDTO, MovieDTO movieDetail) {
        this.user = userDTO;
        this.movies = Collections.singletonList(movieDetail);
    }

}
