package com.servico.user.tmdb.usertmdb.services.rest;

import com.servico.user.tmdb.usertmdb.models.dto.UserDTO;
import com.servico.user.tmdb.usertmdb.utils.constants.Constats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class UserServiceRest {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.get.user.autenticacao}")
    private String urlService;

    public UserDTO getUserDTOWithToken(){
        String url = generateUrlWithToken();
        UserDTO userDTO;
        try {
            userDTO = restTemplate.getForObject(url, UserDTO.class);
            if(Objects.isNull(userDTO)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constats.USER_NOT_FOUND);
            }
        } catch (RestClientResponseException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getRawStatusCode()), e.getResponseBodyAsString());
        }
        return userDTO;
    }

    private String generateUrlWithToken(){
        String token = request.getHeader(Constats.Authorization);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(urlService).
                queryParam("token", token)
                .build();

        return uriComponents.toUriString();
    }
}
