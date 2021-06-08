package com.servico.user.tmdb.usertmdb.services;

import com.servico.user.tmdb.usertmdb.models.dto.MovieDTO;
import com.servico.user.tmdb.usertmdb.models.dto.UserDTO;
import com.servico.user.tmdb.usertmdb.models.responses.MovieUserResponse;
import com.servico.user.tmdb.usertmdb.models.responses.MoviesUserResponse;
import com.servico.user.tmdb.usertmdb.repository.IMovieRepository;
import com.servico.user.tmdb.usertmdb.services.rest.MovieServiceRest;
import com.servico.user.tmdb.usertmdb.services.rest.UserServiceRest;
import com.servico.user.tmdb.usertmdb.utils.request.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListMovieService {

    @Autowired
    private UserServiceRest userServiceRest;

    @Autowired
    private MovieServiceRest movieServiceRest;

    @Autowired
    private UserService userService;

    private IMovieRepository movieRepository;

    public MovieUserResponse addMovie(String idMovie, Language language){
        UserDTO userDTO = userServiceRest.getUserDTOWithToken();
        MovieDTO movieDetail = movieServiceRest.getMovieDetailTMDB(idMovie, language);
        userService.saveUser(userDTO, movieDetail);
        return new MovieUserResponse(userDTO, movieDetail);
    }

    public MoviesUserResponse listMovieUser(String id) {
        MoviesUserResponse response = userService.listMovie(id);
        return response;
    }
}
