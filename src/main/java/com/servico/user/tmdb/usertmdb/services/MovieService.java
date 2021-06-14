package com.servico.user.tmdb.usertmdb.services;

import com.servico.user.tmdb.usertmdb.models.entity.Movie;
import com.servico.user.tmdb.usertmdb.models.entity.User;
import com.servico.user.tmdb.usertmdb.repository.IMovieRepository;
import com.servico.user.tmdb.usertmdb.repository.IUserRepository;
import com.servico.user.tmdb.usertmdb.utils.constants.Constats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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

    public Movie getMovieUser(String id, String movieDTOId){
        int movieId = Integer.parseInt(movieDTOId);
        User user = userRepository.findByIdAndMoviesIdMovieTMDB(id, movieId);
        if(Objects.isNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constats.MOVIE_NONEXIST);
        }
        return findMovieInList(movieId, user);
    }

    private Movie findMovieInList(int movieId, User user) {
        return user.getMovies().stream().filter(movie -> movie.getIdMovieTMDB() == movieId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, Constats.MOVIE_NONEXIST));
    }

    public Movie updateMovieUserWithRating(Movie movie) {
        return updateMovieUserWithRatingInDataBase(movie);
    }

    public Movie updateMovieUserWithRatingInDataBase(Movie movie){
        Movie movieSave;
        try {
            movieSave = movieRepository.save(movie);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constats.FAIL_UPDATE_RATING);
        }
        return movieSave;
    }
}
