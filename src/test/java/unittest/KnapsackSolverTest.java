package unittest;

import com.mobiquity.exception.*;
import com.mobiquity.model.Item;
import com.mobiquity.model.KnapsackProblemInstance;
import com.mobiquity.packer.KnapsackSolver;
import com.mobiquity.util.ConfigProperties;
import com.mobiquity.validation.ValidationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The KnapsackSolverTest class is responsible for testing the KnapsackSolver class.
 */
class KnapsackSolverTest {

    private static int MAX_TOTAL_WEIGHT;
    private static int MAX_ITEM_COUNT;
    private static int MAX_ITEM_WEIGHT;
    private static int MAX_ITEM_COST;

    /**
     * Loads configuration properties before all tests.
     */
    @BeforeAll
    public static void loadConfigProperties() {
        MAX_TOTAL_WEIGHT = ConfigProperties.getPropertyAsInt("MAX_TOTAL_WEIGHT");
        MAX_ITEM_COUNT   = ConfigProperties.getPropertyAsInt("MAX_ITEM_COUNT");
        MAX_ITEM_WEIGHT  = ConfigProperties.getPropertyAsInt("MAX_ITEM_WEIGHT");
        MAX_ITEM_COST    = ConfigProperties.getPropertyAsInt("MAX_ITEM_COST");
    }

    /**
     * Tests the solveKnapsackProblem method with given items.
     */
    @Test
    void shouldSolveKnapsackProblemCorrectlyForGivenItems() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item(1, 53.38, 45),
                new Item(2, 88.62, 98),
                new Item(3, 78.48, 3),
                new Item(4, 72.30, 76),
                new Item(5, 30.18, 9),
                new Item(6, 46.34, 48)
        );
        // Test execution and verification
        String result = KnapsackSolver.solveKnapsackProblem( new KnapsackProblemInstance(81, items) );
        assertEquals("4", result);
    }

    /**
     * Tests the solveKnapsackProblem method when multiple packages have the same price.
     * It should prefer the lighter package.
     */
    @Test
    void shouldPreferLighterPackageWhenMultiplePackagesHaveSamePrice() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item(1, 50.0, 60),
                new Item(2, 49.0, 60),
                new Item(3, 48.0, 60)
        );
        // Test execution and verification
        String result = KnapsackSolver.solveKnapsackProblem(new KnapsackProblemInstance(50, items));
        assertEquals("3", result);
    }

    /**
     * Tests the solveKnapsackProblem method when there is only one item.
     */
    @Test
    void shouldReturnDashWhenThereIsOnlyOneItem() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item (1 ,10 ,60)
        );
        // Test execution and verification
        String result = KnapsackSolver.solveKnapsackProblem(new KnapsackProblemInstance(50, items));
        assertEquals("-", result);
    }

    /**
     * Tests the solveKnapsackProblem method when a single item is heavier than the maximum weight.
     */
    @Test
    void shouldReturnDashWhenSingleItemIsHeavierThanMaxWeight() {
        // Test setup
        List<Item> items = Arrays.asList(new Item (1 ,60 ,100));
        // Test execution and verification
        String result = KnapsackSolver.solveKnapsackProblem(new KnapsackProblemInstance(50, items));
        assertEquals("-", result);
    }

    /**
     * Tests the solveKnapsackProblem method with given items when the maximum weight is reached.
     */
    @Test
    void shouldSolveKnapsackProblemCorrectlyForGivenItemsWhenMaximumWeightReached() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item (1 ,10 ,60),
                new Item (2 ,20 ,100),
                new Item (3 ,30 ,120)
        );
        // Test execution and verification
        String result = KnapsackSolver.solveKnapsackProblem(new KnapsackProblemInstance(50, items));
        assertEquals("2,3", result);
    }

    /**
     * Tests the validateKnapsackProblemInstance method when the minimum weight item is heavier than the total weight.
     */
    @Test
    void shouldThrowExceptionWhenMinWeightItemIsHeavierThanTotalWeight() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item(1, 10.0, 60),
                new Item(2, 20.0, 100),
                new Item(3, 30.0, 120)
        );
        int maxWeight = 9;
        KnapsackProblemInstance knapsackProblemInstance = new KnapsackProblemInstance(maxWeight, items);
        // Test execution and verification
        assertThrows(MinimumWeightHeavierThanTotalWeightException.class , () -> ValidationService.validateKnapsackProblemInstance(knapsackProblemInstance));
    }

    /**
     * Tests the validateMaxWeight method when the maximum weight constraint is violated.
     */
    @Test
    void shouldThrowMaxTotalWeightExceptionWhenMaxWeightConstraintViolated() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item(1, 53.38, 45),
                new Item(2, 88.62, 98),
                new Item(3, 78.48, 3),
                new Item(4, 72.30, 76),
                new Item(5, 30.18, 9),
                new Item(6, 46.34, 48)
        );
        // Test execution and verification
        assertThrows(MaxTotalWeightException.class, () -> ValidationService.validateMaxWeight(MAX_TOTAL_WEIGHT + 1));
    }

    /**
     * Tests the validateItemCount method when the maximum item count constraint is violated.
     */
    @Test
    void shouldThrowMaxItemCountExceptionWhenMaxItemCountConstraintViolated() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item(1, 10.0, 60),
                new Item(2, 20.0, 100),
                new Item(3, 30.0, 120),
                new Item(4, 40.0, 50),
                new Item(5, 50.0, 60),
                new Item(6, 60.0, 70),
                new Item(7, 70.0, 80),
                new Item(8, 80.0, 90),
                new Item(9, 90.0, 100),
                new Item(10, 10.0, 60),
                new Item(11, 20.0, 100),
                new Item(12, 30.0, 120),
                new Item(13, 40.0, 50),
                new Item(14, 50.0, 60),
                new Item(15, 60.0, 70),
                new Item(16, 70.0, 80)
        );
        // Test execution and verification
        assertThrows(MaxItemCountException.class , () -> ValidationService.validateItemCount(items));
    }

    /**
     * Tests the validateItemWeightAndCost method when the maximum item weight constraint is violated.
     */
    @Test
    void shouldThrowMaxItemWeightExceptionWhenMaxItemWeightConstraintViolated() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item (1 ,(MAX_ITEM_WEIGHT +1) ,45)
        );
        // Test execution and verification
        assertThrows(MaxItemWeightException.class , () -> ValidationService.validateItemWeightAndCost(items.get(0)));
    }

    /**
     * Tests the validateItemWeightAndCost method when the maximum item cost constraint is violated.
     */
    @Test
    void shouldThrowMaxItemCostExceptionWhenMaxItemCostConstraintViolated() {
        // Test setup
        List<Item> items = Arrays.asList(
                new Item (1 ,50 ,(MAX_ITEM_COST +1))
        );
        // Test execution and verification
        assertThrows(MaxItemCostException.class , () -> ValidationService.validateItemWeightAndCost(items.get(0)));
    }

    /**
     * Tests if the configuration properties are read correctly.
     */
    @Test
    void shouldReadConfigPropertiesCorrectly() {
        // Test execution and verification
        assertEquals(MAX_TOTAL_WEIGHT , ConfigProperties.getPropertyAsInt("MAX_TOTAL_WEIGHT"));
        assertEquals(MAX_ITEM_COUNT , ConfigProperties.getPropertyAsInt("MAX_ITEM_COUNT"));
        assertEquals(MAX_ITEM_WEIGHT , ConfigProperties.getPropertyAsInt("MAX_ITEM_WEIGHT"));
        assertEquals(MAX_ITEM_COST , ConfigProperties.getPropertyAsInt("MAX_ITEM_COST"));
    }

}
