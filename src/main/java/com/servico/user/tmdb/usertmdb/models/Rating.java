package com.servico.user.tmdb.usertmdb.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Table(name = "avaliacao")
@Entity
@NoArgsConstructor
public class Rating {

    @Id
    private String id;

    @Column(name = "nota")
    private double note;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @Column(name = "resumo_avaliacao")
    private String resume;
}
