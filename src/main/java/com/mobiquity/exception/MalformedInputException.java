package com.mobiquity.exception;

/**
 * This class represents an exception that is thrown when an input string is not in the expected format.
 */
public class MalformedInputException extends APIException {

    /**
     * Constructs a new MalformedInputException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param e The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public MalformedInputException(String message, NumberFormatException e) {
        super(message, e);
    }

    /**
     * Constructs a new MalformedInputException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public MalformedInputException(String message) {
        super(message);
    }
}
