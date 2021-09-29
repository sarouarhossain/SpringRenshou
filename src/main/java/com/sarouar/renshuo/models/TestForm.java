package com.sarouar.renshuo.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TestForm {
  @NotNull(message = "name cant be null.")
  private String name;

  @NotNull(message = "Email cant be null")
  @Email(message = "email pattern not valid")
  private String email;

  @NotNull(message = "Birthdate cant be null")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthdate;
}
