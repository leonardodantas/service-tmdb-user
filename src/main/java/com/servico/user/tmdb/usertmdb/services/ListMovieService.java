package com.servico.user.tmdb.usertmdb.services;

import com.servico.user.tmdb.usertmdb.models.dto.MovieDTO;
import com.servico.user.tmdb.usertmdb.models.dto.UserDTO;
import com.servico.user.tmdb.usertmdb.models.entity.Rating;
import com.servico.user.tmdb.usertmdb.models.entity.User;
import com.servico.user.tmdb.usertmdb.models.responses.MovieUserResponse;
import com.servico.user.tmdb.usertmdb.models.responses.MoviesUserResponse;
import com.servico.user.tmdb.usertmdb.repository.IMovieRepository;
import com.servico.user.tmdb.usertmdb.services.rest.MovieServiceRest;
import com.servico.user.tmdb.usertmdb.services.rest.UserServiceRest;
import com.servico.user.tmdb.usertmdb.utils.request.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

@Service
public class ListMovieService {

    @Autowired
    private UserServiceRest userServiceRest;

    @Autowired
    private MovieServiceRest movieServiceRest;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    public MovieUserResponse addMovie(String idMovie, Language language){
        UserDTO userDTO = userServiceRest.getUserDTOWithToken();
        MovieDTO movieDetail = movieServiceRest.getMovieDetailTMDB(idMovie, language);
        userService.saveUser(userDTO, movieDetail);
        return new MovieUserResponse(userDTO, movieDetail);
    }

    public MoviesUserResponse listMovieUser() {
        Optional<User> user = userService.listMovie();
        if(user.isEmpty()) {
            return new MoviesUserResponse();
        }
        validateRatingWhereIsNull(user.get());
        return MoviesUserResponse.createMovieUserResponseWithAllMovies(user.get());
    }

    private void validateRatingWhereIsNull(User user){
        user.getMovies().stream()
                .filter(movie -> Objects.isNull(movie.getRating()))
                .forEach(movie -> movie.setRating(new Rating()));
    }

}
