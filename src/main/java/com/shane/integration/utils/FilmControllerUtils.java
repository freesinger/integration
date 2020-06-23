package com.shane.integration.utils;

import com.shane.integration.entity.Film;

import java.util.List;

public class FilmControllerUtils {
    public void bulkAddFilms(List<Film> films, List<Film> filmsToAdd) {
        films.addAll(filmsToAdd);
    }
}
