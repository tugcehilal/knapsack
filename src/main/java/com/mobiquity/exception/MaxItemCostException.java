package com.mobiquity.exception;

/**
 * This class represents an exception that is thrown when the cost of an item exceeds the limit.
 */
public class MaxItemCostException extends APIException {

    /**
     * Constructs a new MaxItemCostException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public MaxItemCostException(String message) {
        super(message);
    }
}
