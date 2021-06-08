package com.servico.user.tmdb.usertmdb.repository;

import com.servico.user.tmdb.usertmdb.models.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository<Movie, String> {
}
