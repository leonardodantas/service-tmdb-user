package com.servico.user.tmdb.usertmdb.services;

import com.servico.user.tmdb.usertmdb.models.entity.User;
import com.servico.user.tmdb.usertmdb.repository.IMovieRepository;
import com.servico.user.tmdb.usertmdb.repository.IUserRepository;
import com.servico.user.tmdb.usertmdb.utils.constants.Constats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class MovieService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IMovieRepository movieRepository;

    public void checkMovieIsOnList(String id, int movieDTOId){
        User userList = userRepository.findByIdAndMoviesIdMovieTMDB(id, movieDTOId);
        if(!Objects.isNull(userList)) {
            if(!userList.getMovies().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constats.MOVIE_ALREADY_ADD);
            }
        }
    }
}
