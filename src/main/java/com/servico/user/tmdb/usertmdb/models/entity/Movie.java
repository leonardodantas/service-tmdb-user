package com.servico.user.tmdb.usertmdb.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "filmes")
@AllArgsConstructor
@Entity
@Getter
public class Movie {

    @Id
    private String id;

    @Column(name = "id_movie_tmdb")
    private int idMovieTMDB;

    @Column(name = "nome", length = 120)
    private String name;

    @Setter
    @Column(name = "recomendar")
    private boolean toRecommend;

    @Setter
    @Column(name = "assistido")
    private boolean watched;

    @Setter
    @JoinColumn(name = "rating_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Rating rating;

    public Movie(){}

    public Movie(int idMovie, String name){
        this.id = UUID.randomUUID().toString();
        this.idMovieTMDB = idMovie;
        this.name = name;
    }
}
