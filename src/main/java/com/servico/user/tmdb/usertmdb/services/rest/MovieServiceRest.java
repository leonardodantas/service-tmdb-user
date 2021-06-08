package com.servico.user.tmdb.usertmdb.services.rest;

import com.servico.user.tmdb.usertmdb.models.dto.MovieDTO;
import com.servico.user.tmdb.usertmdb.utils.constants.Constats;
import com.servico.user.tmdb.usertmdb.utils.request.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class MovieServiceRest {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.get.movie.detail}")
    private String urlService;

    public MovieDTO getMovieDetailTMDB(String movieId, Language language){
        String url = generateUrlWithLanguage(language, movieId);
        MovieDTO movieDTO;
        try {
                movieDTO = restTemplate.getForObject(url, MovieDTO.class);
            if(Objects.isNull(movieDTO)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constats.USER_NOT_FOUND);
            }
        } catch (RestClientResponseException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getRawStatusCode()), e.getResponseBodyAsString());
        }
        return movieDTO;
    }

    private String generateUrlWithLanguage(Language language, String movieId){
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(urlService)
                .queryParam("language", language)
                .queryParam("movie", movieId)
                .build();

        return uriComponents.toUriString();
    }
}
