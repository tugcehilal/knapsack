package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.file.FileReader;
import com.mobiquity.model.KnapsackProblemInstance;
import com.mobiquity.validation.ValidationService;

import java.util.List;

/**
 * This class is responsible for packing items into a package.
 */
public class Packer {

  /**
   * Private constructor to prevent instantiation of utility class.
   */
  private Packer() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Packs items into a package.
   *
   * @param filePath The path to the file containing the test cases.
   * @return A string representing the optimal solution for each test case.
   * @throws APIException If an error occurs while reading the file or packing the items.
   */
  public static String pack(String filePath) throws APIException {
    try {
      // Read the test cases from the file
      List<KnapsackProblemInstance> knapsackProblemInstances = FileReader.readTestCasesFromFile(filePath);

      // StringBuilder to build the result string
      StringBuilder resultBuilder = new StringBuilder();

      // Iterate over each test case
      for (KnapsackProblemInstance testCase : knapsackProblemInstances) {
        // Validate the test case
        ValidationService.validateKnapsackProblemInstance(testCase);

        // Solve the knapsack problem for this test case
        String optimalSolution = KnapsackSolver.solveKnapsackProblem(testCase);

        // Append the solution to the result string
        resultBuilder.append(optimalSolution).append("\n");
      }

      // Return the result string, removing the trailing newline
      return resultBuilder.toString().trim();
    } catch (APIException e) {
      throw new APIException(e.getMessage(), e);
    }
  }
}
