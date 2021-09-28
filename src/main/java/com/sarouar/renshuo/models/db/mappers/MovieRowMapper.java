package com.sarouar.renshuo.models.db.mappers;

import com.sarouar.renshuo.models.db.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovieRowMapper implements RowMapper {
  @Override
  public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return new Movie(
        resultSet.getLong("id"),
        resultSet.getString("name"),
        resultSet.getString("director"),
        LocalDateTime.parse(resultSet.getString("release_date"), formatter));
  }
}
