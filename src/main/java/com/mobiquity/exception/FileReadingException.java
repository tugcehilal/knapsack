package com.mobiquity.exception;

/**
 * This class represents an exception that is thrown when an error occurs while reading a file.
 */
public class FileReadingException extends APIException {

    /**
     * Constructs a new FileReadingException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param e The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public FileReadingException(String message, Exception e) {
        super(message, e);
    }
}
