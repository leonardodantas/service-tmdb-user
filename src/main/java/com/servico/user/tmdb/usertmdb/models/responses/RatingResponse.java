package com.servico.user.tmdb.usertmdb.models.responses;

import com.servico.user.tmdb.usertmdb.models.entity.Rating;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class RatingResponse {

    private String id;
    private double note;
    private Date date;
    private String resume;

    public RatingResponse(Rating rating) {
        this.id = rating.getId();
        this.note = rating.getNote();
        this.date = rating.getDate();
        this.resume = rating.getResume();
    }
}
