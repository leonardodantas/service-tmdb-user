package com.servico.user.tmdb.usertmdb.config.security;

import com.google.common.base.Strings;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticatedWithTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.password}")
    private String password;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if(Strings.isNullOrEmpty(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido");
        }
        filterChain.doFilter(request, response);
    }


    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (!Strings.isNullOrEmpty(token)) {
            validadeTokenPassword(token);
            return token;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido");
    }

    private void validadeTokenPassword(String token){
        try{
            String passwordToken = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("password").toString();
            if(!passwordToken.equals(password)){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido");
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido");
        }
    }
}
