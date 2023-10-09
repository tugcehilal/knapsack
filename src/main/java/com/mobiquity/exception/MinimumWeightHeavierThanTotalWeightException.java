package com.mobiquity.exception;

/**
 * This class represents an exception that is thrown when the minimum weight item is heavier than the total weight limit.
 */
public class MinimumWeightHeavierThanTotalWeightException extends APIException {

    /**
     * Constructs a new MinimumWeightHeavierThanTotalWeightException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public MinimumWeightHeavierThanTotalWeightException(String message) {
        super(message);
    }
}
