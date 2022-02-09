package com.sarouar.renshuo.controllers;

import com.sarouar.renshuo.exceptions.ApplicationException;
import com.sarouar.renshuo.models.TestForm;
import com.sarouar.renshuo.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {
  @Autowired private TestService service;

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

  @GetMapping("/four/{id}")
  public ResponseEntity<String> test4(@PathVariable String id) {
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

  @GetMapping("/log")
  public ResponseEntity<String> test5() {
    var res = service.testMethod();
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
}
