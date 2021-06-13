package com.servico.user.tmdb.usertmdb.utils.factory;

import com.servico.user.tmdb.usertmdb.models.entity.Movie;
import com.servico.user.tmdb.usertmdb.models.entity.Rating;
import com.servico.user.tmdb.usertmdb.models.request.RecommendWatchedRequest;
import com.servico.user.tmdb.usertmdb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieFactory {

    @Autowired
    private MovieService movieService;

    public Movie createMovieWithRating(String userId, String movieId, RecommendWatchedRequest recommendWatched){
        Movie movieUser = movieService.getMovieUser(userId, movieId);

        Rating rating = new Rating(recommendWatched);
        movieUser.setRating(rating);
        movieUser.setWatched(true);
        movieUser.setToRecommend(recommendWatched.isToRecommend());

        return movieUser;
    }
}
