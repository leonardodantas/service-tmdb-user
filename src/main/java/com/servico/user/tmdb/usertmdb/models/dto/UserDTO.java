package com.servico.user.tmdb.usertmdb.models.dto;

import com.servico.user.tmdb.usertmdb.models.entity.User;
import lombok.Getter;

@Getter
public class UserDTO {

    private String id;
    private String name;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }
}
