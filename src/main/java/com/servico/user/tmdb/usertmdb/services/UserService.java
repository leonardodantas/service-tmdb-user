package com.servico.user.tmdb.usertmdb.services;

import com.google.common.base.Strings;
import com.servico.user.tmdb.usertmdb.models.dto.MovieDTO;
import com.servico.user.tmdb.usertmdb.models.dto.UserDTO;
import com.servico.user.tmdb.usertmdb.models.entity.Movie;
import com.servico.user.tmdb.usertmdb.models.entity.User;
import com.servico.user.tmdb.usertmdb.models.responses.MovieUserResponse;
import com.servico.user.tmdb.usertmdb.models.responses.MoviesUserResponse;
import com.servico.user.tmdb.usertmdb.repository.IUserRepository;
import com.servico.user.tmdb.usertmdb.services.rest.UserServiceRest;
import com.servico.user.tmdb.usertmdb.utils.constants.Constats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserServiceRest userServiceRest;

    private User getUser(String id){
        Optional<User> user = getUserInDataBase(id);
        return user.orElseGet(User::new);
    }

    public void saveUser(UserDTO userDTO, MovieDTO movieDTO){
        User user;
        User userExist = getUser(userDTO.getId());
        if(Strings.isNullOrEmpty(userExist.getId())){
            user = new User(userDTO, movieDTO);
        } else {
            user = createListMovieUserForUpdate(movieDTO, userExist);
        }
        saveUserInDataBase(user);
    }

    private User createListMovieUserForUpdate(MovieDTO movieDTO, User user) {
        movieService.checkMovieIsOnList(user.getId(), movieDTO.getId());
        List<Movie> movies = user.getMovies();
        movies.add(new Movie(movieDTO.getId(), movieDTO.getTitulo()));
        return new User(user, movies);
    }

    private User saveUserInDataBase(User user){
        User userSave;
        try {
            userSave = userRepository.save(user);
        } catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constats.ERROR_SAVE_USER);
        }
        return userSave;
    }

    private Optional<User> getUserInDataBase(String id) {
        Optional<User> user;
        try {
            user = userRepository.findById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constats.USER_NOT_FOUND);
        }
        return user;
    }

    public Optional<User> listMovie() {
        UserDTO user = userServiceRest.getUserDTOWithToken();
        Optional<User> userWithMovie = userRepository.findById(user.getId());
        return userWithMovie;
    }

}
