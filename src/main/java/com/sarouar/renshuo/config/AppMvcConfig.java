package com.sarouar.renshuo.config;

import com.sarouar.renshuo.interceptors.LoggingInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AppMvcConfig implements WebMvcConfigurer {
  @Autowired LoggingInterceptors loggingInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loggingInterceptor);
  }
}
