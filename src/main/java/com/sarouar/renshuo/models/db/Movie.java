package com.sarouar.renshuo.models.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Movie {
  private Long id;
  private String name;
  private String director;
  private LocalDateTime releaseDate;
}
