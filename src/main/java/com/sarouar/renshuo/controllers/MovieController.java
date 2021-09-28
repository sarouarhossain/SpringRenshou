package com.sarouar.renshuo.controllers;

import com.sarouar.renshuo.models.db.Movie;
import com.sarouar.renshuo.models.form.MovieForm;
import com.sarouar.renshuo.repositories.MovieRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {
  private final MovieRepository movieRepository;

  public MovieController(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @PostMapping("")
  public ResponseEntity<Integer> insert(@RequestBody MovieForm form) {
    Movie movie =
        new Movie(null, form.getName(), form.getDirector(), form.getReleaseDate().atStartOfDay());
    int response = movieRepository.insertMovies(movie);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("")
  public ResponseEntity<List<Movie>> getAll() {
    List<Movie> movies = movieRepository.selectMovies();
    return new ResponseEntity<>(movies, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> get(@PathVariable("id") Integer id) {
    Optional<Movie> optional = movieRepository.selectMovieById(id);
    return optional
        .<ResponseEntity<Object>>map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND));
  }
}
