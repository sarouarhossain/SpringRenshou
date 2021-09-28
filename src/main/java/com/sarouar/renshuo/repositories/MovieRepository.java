package com.sarouar.renshuo.repositories;

import com.sarouar.renshuo.models.db.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
  List<Movie> selectMovies();

  int insertMovies(Movie movie);

  int deleteMovies(int id);

  Optional<Movie> selectMovieById(int id);
}
