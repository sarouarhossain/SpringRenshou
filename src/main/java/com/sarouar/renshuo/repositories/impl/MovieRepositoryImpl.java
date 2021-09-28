package com.sarouar.renshuo.repositories.impl;

import com.sarouar.renshuo.models.db.Movie;
import com.sarouar.renshuo.models.db.mappers.MovieRowMapper;
import com.sarouar.renshuo.repositories.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
  private final JdbcTemplate jdbcTemplate;

  public MovieRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Movie> selectMovies() {
    String sql = "SELECT * FROM movie;";
    return jdbcTemplate.query(sql, new MovieRowMapper());
  }

  @Override
  public int insertMovies(Movie movie) {
    String sql = "INSERT INTO movie(name, director, release_date) VALUES (?, ?, ?);";
    return jdbcTemplate.update(sql, movie.getName(), movie.getDirector(), movie.getReleaseDate());
  }

  @Override
  public int deleteMovies(int id) {
    var sql = "DELETE FROM movie WHERE id = ?";
    return jdbcTemplate.update(sql, id);
  }

  @Override
  public Optional<Movie> selectMovieById(int id) {
    var sql = "SELECT * FROM movie WHERE id = ? ";
    return jdbcTemplate.query(sql, new MovieRowMapper(), id).stream().findFirst();
  }
}
