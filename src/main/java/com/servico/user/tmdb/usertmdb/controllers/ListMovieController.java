package com.servico.user.tmdb.usertmdb.controllers;

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

@Api(tags = "Serviço para criação de lista de filmes do usuario")
@RestController
@RequestMapping("/listmovie")
public class ListMovieController {

    @Autowired
    private ListMovieService listMovieService;

    @PostMapping("/movie/add")
    @ApiOperation(tags = "Criação de lista de filmes do usuario", value = "Adicionar um novo filme na lista")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Created", response = MovieUserResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar", response = ErrorResponse.class)
    })
    public ResponseEntity<?> addMovieInList(@Valid @RequestBody MovieRequest body,
                                            @ApiParam(required = true, value = "Idioma do filme")
                                            @RequestParam Language language){
        MovieUserResponse response = listMovieService.addMovie(body.getIdMovie(), language);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/movies/user")
    @ApiOperation(tags = "Lista de filmes do usuario", value = "Exibe todos os filmes de um usuario pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Created", response = MovieUserResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar", response = ErrorResponse.class)
    })
    public ResponseEntity<?> listMovieUser(@ApiParam(required = true, value = "id")
                                               @RequestParam String id){
        MoviesUserResponse response = listMovieService.listMovieUser(id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
