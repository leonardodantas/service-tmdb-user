package com.servico.user.tmdb.usertmdb.controllers;

import com.servico.user.tmdb.usertmdb.models.error.ErrorResponse;
import com.servico.user.tmdb.usertmdb.models.request.RecommendWatchedRequest;
import com.servico.user.tmdb.usertmdb.models.responses.MovieUserResponse;
import com.servico.user.tmdb.usertmdb.models.responses.MoviesUserResponse;
import com.servico.user.tmdb.usertmdb.services.RecommendWatchedService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Recomendações")
@RestController
@RequestMapping("v1/recommend-watched")
public class RecommendWatchedController {

    @Autowired
    private RecommendWatchedService recommendWatchedService;

    @PostMapping
    @ApiOperation(tags = "Recomendações", value = "Criar recomendação de um filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Created", response = MovieUserResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar", response = ErrorResponse.class)
    })
    public ResponseEntity<?> userWatchMovie(
            @ApiParam(value = "Id do filme para avaliação", name = "movieId")
            @RequestParam String movieId,
            @Valid @RequestBody RecommendWatchedRequest body){

        MoviesUserResponse response = recommendWatchedService.userWatchMovie(movieId, body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping
    @ApiOperation(tags = "Recomendações", value = "Atualizar recomendação de um filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Created", response = MovieUserResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar", response = ErrorResponse.class)
    })
    public ResponseEntity<?> updateRecommendMovie(
            @ApiParam(value = "Id do filme para avaliação", name = "movieId")
            @RequestParam String movieId,
            @Valid @RequestBody RecommendWatchedRequest body){

        MoviesUserResponse response = recommendWatchedService.updateRecommendMovie(movieId, body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
