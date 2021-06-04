package com.servico.user.tmdb.usertmdb.controllers;

import com.servico.user.tmdb.usertmdb.models.request.MovieRequest;
import com.servico.user.tmdb.usertmdb.services.ListMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Serviço para criação de lista de filmes do usuario")
@RestController
@RequestMapping("/listmovie")
public class ListMovieController {

    @Autowired
    private ListMovieService listMovieService;

    @PostMapping("/movie/add")
    @ApiOperation(tags = "Serviço para criação de lista de filmes do usuario", value = "Adicionar um novo filme na lista")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> addMovieInList(@Valid @RequestBody MovieRequest movieRequest){

        return null;
    }

}
