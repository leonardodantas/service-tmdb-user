package com.servico.user.tmdb.usertmdb.models.entity;

import com.servico.user.tmdb.usertmdb.models.request.RecommendWatchedRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Table(name = "avaliacao")
@Entity
@NoArgsConstructor
@Getter
public class Rating {

    @Id
    private String id;

    @Column(name = "nota")
    private double note;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "resumo_avaliacao")
    private String resume;

    public Rating(RecommendWatchedRequest recommendWatched) {
        this.id = UUID.randomUUID().toString();
        this.note = recommendWatched.getNote();
        this.date = Calendar.getInstance().getTime();
        this.resume = recommendWatched.getResume();
    }
}
