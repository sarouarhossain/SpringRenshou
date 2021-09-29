package com.sarouar.renshuo.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
  private String code;
  private String info;
  private HttpStatus statusCode;

  public ApplicationException() {}

  public ApplicationException(String message) {
    super(message);
  }

  public ApplicationException(String message, Throwable e) {
    super(message, e);
  }

  /**
   * Constructs a new runtime exception with the specified detail message and cause.
   *
   * <p>Note that the detail message associated with {@code cause} is <i>not</i> automatically
   * incorporated in this runtime exception's detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the {@link
   *     #getMessage()} method).
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A {@code null} value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   * @since 1.4
   */
  public ApplicationException(
      String message, Throwable cause, String code, String info, HttpStatus statusCode) {
    super(message, cause);
    this.code = code;
    this.info = info;
    this.statusCode = statusCode;
  }

  /**
   * Constructs a new runtime exception with {@code null} as its detail message. The cause is not
   * initialized, and may subsequently be initialized by a call to {@link #initCause}.
   */
  public ApplicationException(String code, String message, String info, HttpStatus statusCode) {
    super(message);
    this.code = code;
    this.info = info;
    this.statusCode = statusCode;
  }

  public String getCode() {
    return code;
  }

  public String getInfo() {
    return info;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }
}
