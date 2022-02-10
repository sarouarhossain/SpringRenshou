package com.sarouar.renshuo.config;

import org.slf4j.MDC;
import org.springframework.core.task.TaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class ContextAwareExecutorDecorator implements Executor, TaskExecutor {
  private final Executor executor;

  public ContextAwareExecutorDecorator(Executor executor) {
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

  private Runnable decorateContextAware(Runnable command) {
    final Map<String, String> originalContextCopy = MDC.getCopyOfContextMap();
    return () -> {
      final Map<String, String> localContextCopy = MDC.getCopyOfContextMap();

      MDC.clear();

      if (originalContextCopy != null) {
        MDC.setContextMap(originalContextCopy);
      }

      command.run();

      MDC.clear();

      if (localContextCopy != null) {
        MDC.setContextMap(localContextCopy);
      }
    };
  }
}
