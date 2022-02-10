package com.sarouar.renshuo.config;

import com.sarouar.renshuo.interceptors.LoggingInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executor;

@Component
public class AppMvcConfig implements WebMvcConfigurer {
  @Autowired LoggingInterceptors loggingInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loggingInterceptor);
  }

  @Bean(name = "workerExecutor")
  public Executor threadPoolTaskScheduler() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(7);
    executor.setMaxPoolSize(42);
    executor.setQueueCapacity(11);
    executor.setThreadNamePrefix("worker-thread-");
    executor.initialize();
    return new ContextAwareExecutorDecorator(executor);
  }
}
