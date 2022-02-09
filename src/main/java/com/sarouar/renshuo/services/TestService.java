package com.sarouar.renshuo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {
  public String testMethod() {
    log.info("Hello from service method");
    return "test 1234.";
  }
}
