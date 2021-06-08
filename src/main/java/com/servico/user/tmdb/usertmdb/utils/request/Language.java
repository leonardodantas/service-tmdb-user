package com.servico.user.tmdb.usertmdb.utils.request;

public enum Language {

    PT("&language=pt-BR"),EN("&language=en-EN"),ES("&language=es-ES");

    private String language;

    Language(String language){
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}