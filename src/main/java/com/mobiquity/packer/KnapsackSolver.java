package com.mobiquity.packer;
import com.mobiquity.model.Item;
import com.mobiquity.model.KnapsackProblemInstance;
import com.mobiquity.util.ConfigProperties;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * The KnapsackSolver class is responsible for solving the knapsack problem.
 * It uses a dynamic programming approach to find the optimal solution.
 */
public class KnapsackSolver {
    // Constants for scaling factors
    private static final int WEIGHT_SCALE_FACTOR = ConfigProperties.getPropertyAsInt("WEIGHT_SCALE_FACTOR");
    private static final int COST_SCALE_FACTOR = ConfigProperties.getPropertyAsInt("COST_SCALE_FACTOR");

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private KnapsackSolver() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Solves the knapsack problem for a given KnapsackProblemInstance.
     * If there is only one item in the package, it returns "-".
     * Otherwise, it uses a dynamic programming approach to find the optimal solution.
     *
     * @param knapsackProblemInstance The instance of the knapsack problem to be solved.
     * @return A string representing the optimal solution. It contains the indices of the items to be included in the package,
     *         separated by commas. If there is only one item in the package, it returns "-".
     */
    public static String solveKnapsackProblem(KnapsackProblemInstance knapsackProblemInstance) {
        List<Item> itemList = knapsackProblemInstance.itemList();
        // If there is only one item in the package, return "-"
        if (itemList.size() == 1) {
            return "-";
        }
        int maxWeight = knapsackProblemInstance.maxWeight();
        // Sort items based on their cost-to-weight ratio so it would be more cost-efficient
        Collections.sort(itemList);
        // Initialize dynamic programming table
        double[][] knapsackSolutionTable = new double[itemList.size() + 1][maxWeight * WEIGHT_SCALE_FACTOR + 1];
        // Fill up dynamic programming table
        for (int i = 1; i <= itemList.size(); i++) {
            Item item = itemList.get(i - 1);
            int itemWeight = (int) Math.round(item.weight() * WEIGHT_SCALE_FACTOR); // Scaling the weight and rounding to an integer for performance. This may cause some loss of precision.
            for (int j = 1; j <= maxWeight * WEIGHT_SCALE_FACTOR; j++) {
                if (itemWeight > j) {
                    knapsackSolutionTable[i][j] = knapsackSolutionTable[i - 1][j];
                } else {
                    knapsackSolutionTable[i][j] = Math.max(knapsackSolutionTable[i - 1][j], knapsackSolutionTable[i - 1][j - itemWeight] + item.cost() * COST_SCALE_FACTOR);
                }
            }
        }
        // Backtrack to find optimal solution
        List<Integer> resultItems = new ArrayList<>();
        int i = itemList.size();
        int j = maxWeight * WEIGHT_SCALE_FACTOR;
        while (i > 0 && j > 0) {
            if (knapsackSolutionTable[i][j] != knapsackSolutionTable[i - 1][j]) {
                Item item = itemList.get(i - 1);
                resultItems.add(item.index());
                j -= item.weight() * WEIGHT_SCALE_FACTOR; // scale down the weight otherwise the
            }
            i--;
        }
        // Return result as string
        if (resultItems.isEmpty()) {
            return "-";
        } else {
            Collections.sort(resultItems);
            StringBuilder sb = new StringBuilder();
            for (int index : resultItems) {
                sb.append(index).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }
}
