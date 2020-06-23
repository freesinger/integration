package com.shane.integration.controller;

import com.shane.integration.entity.Film;
import com.shane.integration.utils.FilmControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FilmController {
    private List<Film> films = new ArrayList<>();
    private FilmControllerUtils filmControllerUtils = new FilmControllerUtils();
    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @PostMapping(value = "/film", params = {"type=1"})
    public ResponseEntity<List<Film>> addFilm(@RequestBody Film film) {
        films.add(film);
        return ResponseEntity.ok(films);
    }

    /**
     * 控制器不应该提供方法重载
     * Controller should not be a dynamic content provider.
     * You need a @Service instance. So you can implement Controller once and use multiple Service implementation.
     * This is the main idea of Spring MVC and DI
     * @ref: https://stackoverflow.com/questions/29751416/how-to-override-requestmapping-in-another-controller
     *
     * @param filmsToAdd
     * @return
     */
    @PostMapping(value = "/film", params = {"type=2"})
    public ResponseEntity<List<Film>> addFilm(@RequestBody List<Film> filmsToAdd) {
        filmControllerUtils.bulkAddFilms(films, filmsToAdd);
        return ResponseEntity.ok(films);
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity deleteFilmById(@PathVariable("id") int id) {
        try {
            films.remove(id);
        } catch (Exception e) {
            logger.error("Delete by id {} failed!", id);
            e.printStackTrace();
        }
        return ResponseEntity.ok(films);
    }

    @GetMapping(value = "/film")
    public ResponseEntity getFilmById(@RequestParam("name") String name) {
        List<Film> results = films.stream().filter(film -> film.getName().equals(name)).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }
}
