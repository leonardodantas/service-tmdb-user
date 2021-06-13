package com.servico.user.tmdb.usertmdb.repository;

import com.servico.user.tmdb.usertmdb.models.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepository extends JpaRepository<Rating, String> {
}
