package com.sarouar.renshuo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class TestService {
  private final Executor executor;

  @Autowired
  public TestService(@Qualifier("workerExecutor") Executor executor) {
    this.executor = executor;
  }

  public String testMethod() {
    log.info("Hello from service method");
    return getMessage().join();
  }

  private CompletableFuture<String> getMessage() {
    return CompletableFuture.supplyAsync(
        () -> {
          log.info("Hello from different thread call");
          return "test 1234";
        },
        executor);
  }
}
