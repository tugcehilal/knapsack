package com.mobiquity.exception;

/**
 * This class represents an exception that is thrown when the number of items exceeds the limit.
 */
public class MaxItemCountException extends APIException{

    /**
     * Constructs a new MaxItemCountException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public MaxItemCountException(String message) {
        super(message);
    }
}
