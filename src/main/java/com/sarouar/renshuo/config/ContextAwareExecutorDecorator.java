package com.sarouar.renshuo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.task.TaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

@Slf4j
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
    // Caller thread MDC copy
    final Map<String, String> orgCopy = ThreadContext.getContext();

    return () -> {
      ThreadContext.put("test", "Test Loc Data");
      // Worker thread MDC copy
      final Map<String, String> locCopy = ThreadContext.getContext();

      if (orgCopy != null) {
        ThreadContext.clearMap();
        ThreadContext.putAll(orgCopy);
      }

      command.run();

      log.info("Test1");
      System.out.println("TestData1: " + ThreadContext.get("test"));

      ThreadContext.clearMap();

      if (locCopy != null) {
        ThreadContext.putAll(locCopy);
      }

      log.info("Test2");
      System.out.println("TestData2: " + ThreadContext.get("test"));
    };
  }
}
