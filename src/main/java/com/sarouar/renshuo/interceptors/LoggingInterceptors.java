package com.sarouar.renshuo.interceptors;

import com.sarouar.renshuo.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class LoggingInterceptors implements HandlerInterceptor {
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

    String transactionId = UUID.randomUUID().toString();
    long startTime = Instant.now().toEpochMilli();
    // MDC.put(AppConstants.TRANSACTION_ID, transactionId);

    ThreadContext.put(AppConstants.TRANSACTION_ID, transactionId);
    request.setAttribute(AppConstants.START_TIME, startTime);
    log.info("Request started.");
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    long startTime = (Long) request.getAttribute(AppConstants.START_TIME);
    log.info("Request Finished. Time Taken = " + (Instant.now().toEpochMilli() - startTime));
    // MDC.clear();
    ThreadContext.clearAll();
  }
}
