package com.servico.user.tmdb.usertmdb.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "usuarios")
@Entity
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column(name = "nome", length = 120)
    private String name;

    @OneToMany
    @JoinColumn(name = "usuario_id")
    private List<Movie> movies;

}
