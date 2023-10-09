package com.mobiquity.validation;

import com.mobiquity.exception.*;
import com.mobiquity.model.Item;
import com.mobiquity.model.KnapsackProblemInstance;
import com.mobiquity.util.ConfigProperties;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is responsible for validating the knapsack problem instance.
 */
public class ValidationService {

    private static final int MAX_TOTAL_WEIGHT = ConfigProperties.getPropertyAsInt("MAX_TOTAL_WEIGHT");
    private static final int MAX_ITEM_COUNT = ConfigProperties.getPropertyAsInt("MAX_ITEM_COUNT");
    private static final int MAX_ITEM_WEIGHT = ConfigProperties.getPropertyAsInt("MAX_ITEM_WEIGHT");
    private static final int MAX_ITEM_COST = ConfigProperties.getPropertyAsInt("MAX_ITEM_COST");

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private ValidationService() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Validates the knapsack problem instance.
     *
     * @param knapsackProblemInstance The instance of the knapsack problem to be validated.
     * @throws APIException If the validation fails.
     */
    public static void validateKnapsackProblemInstance(KnapsackProblemInstance knapsackProblemInstance) throws APIException {
        List<Item> items = knapsackProblemInstance.itemList();
        validateItemCount(items);
        validateMaxWeight(knapsackProblemInstance.maxWeight());
        Item minWeightItem = Collections.min(items, Comparator.comparing(Item::weight));
        if (items.size()> 1 && minWeightItem.weight() > knapsackProblemInstance.maxWeight()) {
            throw new MinimumWeightHeavierThanTotalWeightException("The minimum weight item is heavier than the total weight limit.");
        }
        for (Item item : items) {
            validateItemWeightAndCost(item);
        }
    }

    /**
     * Validates the maximum weight of the package.
     *
     * @param maxWeight The maximum weight of the package.
     * @throws APIException If the maximum weight exceeds the limit.
     */
    public static void validateMaxWeight(int maxWeight) throws APIException {
        if (maxWeight > MAX_TOTAL_WEIGHT) {
            throw new MaxTotalWeightException("The maximum weight that a package can take is "+ MAX_TOTAL_WEIGHT);
        }
    }

    /**
     * Validates the number of items in the package.
     *
     * @param items The list of items in the package.
     * @throws APIException If the number of items exceeds the limit.
     */
    public static void validateItemCount(List<Item> items) throws APIException {
        if (items.size() > MAX_ITEM_COUNT) {
            throw new MaxItemCountException("There might be up to "+ MAX_ITEM_COUNT +" items you need to choose from");
        }
    }

    /**
     * Validates the weight and cost of an item.
     *
     * @param item The item to be validated.
     * @throws APIException If the weight or cost of the item exceeds the limit.
     */
    public static void validateItemWeightAndCost(Item item) throws APIException {
        if (item.weight() > MAX_ITEM_WEIGHT) {
            throw new MaxItemWeightException("The maximum weight of an item should be less than or equal to "+MAX_ITEM_WEIGHT);
        }
        if (item.cost() > MAX_ITEM_COST){
            throw new MaxItemCostException("The maximum cost of an item should be less than or equal to "+MAX_ITEM_COST);
        }
    }

}
