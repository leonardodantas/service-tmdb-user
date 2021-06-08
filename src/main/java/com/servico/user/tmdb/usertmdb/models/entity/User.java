package com.servico.user.tmdb.usertmdb.models.entity;

import com.servico.user.tmdb.usertmdb.models.dto.MovieDTO;
import com.servico.user.tmdb.usertmdb.models.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Table(name = "usuarios")
@Entity
@Getter
@Builder
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Column(name = "nome", length = 120)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Movie> movies;

    public User(){}

    public User(UserDTO userDTO, MovieDTO movieDTO) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.movies = Collections.singletonList(new Movie(movieDTO.getId(), movieDTO.getTitulo()));
    }

    public User(User user, List<Movie> movies) {
        this.id = user.getId();
        this.name = user.getName();
        this.movies = movies;
    }
}
