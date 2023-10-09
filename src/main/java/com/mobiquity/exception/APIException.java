package com.mobiquity.exception;

/**
 * This class represents a general API exception.
 */
public class APIException extends Exception {

  /**
   * Constructs a new API exception with the specified detail message and cause.
   *
   * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
   * @param e The cause (which is saved for later retrieval by the Throwable.getCause() method).
   */
  public APIException(String message, Exception e) {
    super(message, e);
  }

  /**
   * Constructs a new API exception with the specified detail message.
   *
   * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
   */
  public APIException(String message) {
    super(message);
  }
}
