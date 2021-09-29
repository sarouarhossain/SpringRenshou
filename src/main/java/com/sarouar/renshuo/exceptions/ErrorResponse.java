package com.sarouar.renshuo.exceptions;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
  private List<Error> errors;
  private ZonedDateTime timestamp;

  public ErrorResponse(Error error) {
    errors = new ArrayList<>(List.of(error));
    timestamp = ZonedDateTime.now();
  }

  public ErrorResponse(List<Error> errors) {
    this.errors = errors;
    timestamp = ZonedDateTime.now();
  }
}
