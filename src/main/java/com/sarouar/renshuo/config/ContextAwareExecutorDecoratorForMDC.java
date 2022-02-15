package com.sarouar.renshuo.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.task.TaskExecutor;

import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import java.util.concurrent.Executor;

@Slf4j
public class ContextAwareExecutorDecoratorForMDC implements Executor, TaskExecutor {
  private final Executor executor;

  public ContextAwareExecutorDecoratorForMDC(Executor executor) {
    this.executor = executor;
  }

  /**
   * Executes the given command at some time in the future. The command may execute in a new thread,
   * in a pooled thread, or in the calling thread, at the discretion of the {@code Executor}
   * implementation.
   *
   * @param command the runnable task
   * @throws RejectedExecutionException if this task cannot be accepted for execution
   * @throws NullPointerException if command is null
   */
  @Override
  public void execute(Runnable command) {
    Runnable ctxAwareCommand = decorateContextAware(command);
    executor.execute(ctxAwareCommand);
  }

  /**
   * Decorate the local thread MDC context
   *
   * @param command command to execute
   * @return runnable
   */
  private Runnable decorateContextAware(Runnable command) {
    // Caller thread MDC copy
    final Map<String, String> originalContextCopy = MDC.getCopyOfContextMap();
    return () -> {
      MDC.put("test", "Test Data");
      // Worker thread MDC copy
      final Map<String, String> localContextCopy = MDC.getCopyOfContextMap();

      MDC.clear();

      // Set caller thread MDC copy
      if (originalContextCopy != null) {
        MDC.setContextMap(originalContextCopy);
      }

      command.run();

      log.warn("Test1");
      System.out.println("Test1: " + MDC.get("test"));

      MDC.clear();

      // Recover local thread MDC copy
      if (localContextCopy != null) {
        MDC.setContextMap(localContextCopy);
      }
      log.warn("Test2");
      System.out.println("Test2: " + MDC.get("test"));
    };
  }
}
