package com.mobiquity.exception;

/**
 * This class represents an exception that is thrown when the weight of an item exceeds the limit.
 */
public class MaxItemWeightException extends APIException{

    /**
     * Constructs a new MaxItemWeightException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public MaxItemWeightException(String message) {
        super(message);
    }
}
