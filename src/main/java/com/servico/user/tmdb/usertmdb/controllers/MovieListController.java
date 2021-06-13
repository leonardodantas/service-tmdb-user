package com.servico.user.tmdb.usertmdb.controllers;

import com.google.common.base.Strings;
import com.servico.user.tmdb.usertmdb.models.error.ErrorResponse;
import com.servico.user.tmdb.usertmdb.models.request.MovieRequest;
import com.servico.user.tmdb.usertmdb.models.responses.MovieUserResponse;
import com.servico.user.tmdb.usertmdb.models.responses.MoviesUserResponse;
import com.servico.user.tmdb.usertmdb.services.ListMovieService;
import com.servico.user.tmdb.usertmdb.utils.request.Language;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Filmes")
@RestController
@RequestMapping("v1/movie-list")
public class MovieListController {

    @Autowired
    private ListMovieService listMovieService;

    @PostMapping("/movie/add")
    @ApiOperation(tags = "Filmes", value = "Adicionar um filme na lista do usuario")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Created", response = MovieUserResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar", response = ErrorResponse.class)
    })
    public ResponseEntity<?> addMovieInList(@Valid @RequestBody MovieRequest body,
                                            @ApiParam(required = true, value = "Idioma do filme")
                                            @RequestParam Language language){
        MovieUserResponse response = listMovieService.addMovie(body.getMovieId(), language);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/movies/user")
    @ApiOperation(tags = "Filmes", value = "Exibe os filmes do usuario")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Created", response = MovieUserResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar", response = ErrorResponse.class)
    })
    public ResponseEntity<?> listMovieUser(){
        MoviesUserResponse response = listMovieService.listMovieUser();
        if(Strings.isNullOrEmpty(response.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
