package com.servico.user.tmdb.usertmdb.config.security;

import com.google.common.base.Strings;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticatedWithTokenFilter extends OncePerRequestFilter {

    private final String secret;
    private final String password;
    private HttpServletResponse response;

    public AuthenticatedWithTokenFilter(String secret, String password){
        this.secret = secret;
        this.password = password;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        this.response = response;
        String token = getToken(request);
        if(Strings.isNullOrEmpty(token)){
            this.sendUnAuthorized();
        }
        filterChain.doFilter(request, response);
    }

    private void sendUnAuthorized() throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Required headers not specified in the request");
    }

    private String getToken(HttpServletRequest request) throws IOException {
        String token = request.getHeader("Authorization");
        if (!Strings.isNullOrEmpty(token)) {
            validadeTokenPassword(token);
            return token;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido");
    }

    private void validadeTokenPassword(String token) throws IOException {
        try{
            String passwordToken = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("password").toString();
            if(!passwordToken.equals(password)){
                this.sendUnAuthorized();
            }
        }catch (Exception e){
            this.sendUnAuthorized();
        }
    }
}
