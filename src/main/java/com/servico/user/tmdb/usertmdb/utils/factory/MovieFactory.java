package com.servico.user.tmdb.usertmdb.utils.factory;

import com.servico.user.tmdb.usertmdb.models.entity.Movie;
import com.servico.user.tmdb.usertmdb.models.entity.Rating;
import com.servico.user.tmdb.usertmdb.models.request.RecommendWatchedRequest;
import com.servico.user.tmdb.usertmdb.services.MovieService;
import com.servico.user.tmdb.usertmdb.utils.constants.Constats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class MovieFactory {

    @Autowired
    private MovieService movieService;

    public Movie createMovieWithRating(String userId, String movieId, RecommendWatchedRequest recommendWatched){
        Movie movieUser = movieService.getMovieUser(userId, movieId);
        createMovieWithRating(recommendWatched, movieUser);
        return movieUser;
    }


    public Movie updateMovieWithRating(String userId, String movieId, RecommendWatchedRequest recommendWatched){
        Movie movieUser = movieService.getMovieUser(userId, movieId);
        if(!movieUser.isWatched()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constats.MOVIE_NOT_WATCHED);
        }
        createMovieWithRating(recommendWatched, movieUser);
        return movieUser;
    }

    private void createMovieWithRating(RecommendWatchedRequest recommendWatched, Movie movieUser) {
        Rating rating = new Rating(recommendWatched);
        movieUser.setRating(rating);
        movieUser.setWatched(true);
        movieUser.setToRecommend(recommendWatched.isToRecommend());
    }
}
