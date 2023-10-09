package com.mobiquity.model;

import java.util.List;

/**
 * This class represents an instance of the knapsack problem.
 * It includes the maximum weight that a package can take and a list of items to choose from.
 */
public record KnapsackProblemInstance(int maxWeight, List<Item> itemList) {
    /**
     * @param maxWeight The maximum weight that the package can take.
     * @param itemList The list of items to choose from.
     */
}
