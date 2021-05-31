package com.servico.user.tmdb.usertmdb.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "filmes")
@Entity
@NoArgsConstructor
public class Movie {

    @Id
    private String id;

    @Column(name = "nome", length = 120)
    private String name;

    @Column(name = "recomendar")
    private boolean toRecommend;

    @Column(name = "assistido")
    private boolean watched;

    @OneToOne
    private Rating rating;
}
