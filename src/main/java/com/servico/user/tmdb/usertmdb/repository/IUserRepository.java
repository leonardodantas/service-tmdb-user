package com.servico.user.tmdb.usertmdb.repository;

import com.servico.user.tmdb.usertmdb.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {
    User findByIdAndMoviesIdMovieTMDB(String id, int movieId);
}
