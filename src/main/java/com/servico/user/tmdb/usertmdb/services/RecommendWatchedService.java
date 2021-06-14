package com.servico.user.tmdb.usertmdb.services;

import com.servico.user.tmdb.usertmdb.models.dto.UserDTO;
import com.servico.user.tmdb.usertmdb.models.entity.Movie;
import com.servico.user.tmdb.usertmdb.models.request.RecommendWatchedRequest;
import com.servico.user.tmdb.usertmdb.models.responses.MoviesUserResponse;
import com.servico.user.tmdb.usertmdb.services.rest.MovieServiceRest;
import com.servico.user.tmdb.usertmdb.services.rest.UserServiceRest;
import com.servico.user.tmdb.usertmdb.utils.factory.MovieFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendWatchedService {

    @Autowired
    private MovieServiceRest movieServiceRest;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserServiceRest userServiceRest;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieFactory movieFactory;

    public MoviesUserResponse userWatchMovie(String movieId, RecommendWatchedRequest recommendWatched) {
        UserDTO userDTO = verifyMovieAndReturnUserDTO(movieId);
        Movie movieUser = movieFactory.createMovieWithRating(userDTO.getId(), movieId, recommendWatched);
        return updateMovieWithRatingAndCreateMovieUserResponse(userDTO, movieUser);
    }

    public MoviesUserResponse updateRecommendMovie(String movieId, RecommendWatchedRequest recommendWatched) {
        UserDTO userDTO = verifyMovieAndReturnUserDTO(movieId);
        Movie movieUser = movieFactory.updateMovieWithRating(userDTO.getId(), movieId, recommendWatched);
        return updateMovieWithRatingAndCreateMovieUserResponse(userDTO, movieUser);
    }

    private MoviesUserResponse updateMovieWithRatingAndCreateMovieUserResponse(UserDTO userDTO, Movie movieUser) {
        Movie movie = movieService.updateMovieUserWithRating(movieUser);
        return MoviesUserResponse.createMovieUserResponseWithOneMovie(userDTO, movie);
    }

    private UserDTO verifyMovieAndReturnUserDTO(String movieId) {
        movieServiceRest.getMovieDetailTMDB(movieId);
        return userServiceRest.getUserDTOWithToken();
    }

}
