package com.sarouar.renshuo.controllers;

import com.sarouar.renshuo.exceptions.ApplicationException;
import com.sarouar.renshuo.models.TestForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {
  @GetMapping("")
  public ResponseEntity<String> test() throws ApplicationException {
    throw new ApplicationException("Test");
    // return new ResponseEntity<>("Testing is ok....", HttpStatus.OK);
  }

  @GetMapping("/two")
  public ResponseEntity<String> test2() {
    throw new IllegalArgumentException("LLLLL");
    // return new ResponseEntity<>("Testing is ok....", HttpStatus.OK);
  }

  @GetMapping("/three")
  public ResponseEntity<Object> test3(@Valid @RequestBody TestForm form) {
    // throw new IllegalArgumentException("LLLLL");
    return new ResponseEntity<>(form, HttpStatus.OK);
  }
}
