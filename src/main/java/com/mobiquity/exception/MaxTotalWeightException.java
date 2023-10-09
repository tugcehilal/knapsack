package com.mobiquity.exception;

/**
 * This class represents an exception that is thrown when the total weight of a package exceeds the limit.
 */
public class MaxTotalWeightException extends APIException{

    /**
     * Constructs a new MaxTotalWeightException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public MaxTotalWeightException(String message) {
        super(message);
    }
}
