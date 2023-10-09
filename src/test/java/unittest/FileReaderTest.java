package unittest;

import com.mobiquity.exception.MalformedInputException;
import com.mobiquity.file.FileReader;
import com.mobiquity.model.Item;
import com.mobiquity.model.KnapsackProblemInstance;
import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTest {

    /**
     * Tests the readTestCasesFromFile method of the FileReader class.
     * It reads the test cases from a file and asserts that the returned list of KnapsackProblemInstance objects is not null and has the correct size.
     * It also checks the properties of the first KnapsackProblemInstance in the list.
     *
     * @throws APIException If an error occurs while reading the file.
     */
    /**
     * Tests the readTestCasesFromFile method with a valid file.
     *
     * @throws IOException If an error occurs while creating the temporary file.
     */
    @Test
    void shouldReadTestCasesFromFileCorrectly() throws IOException, APIException {
        // Create a temporary file with test case data
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, Arrays.asList(
                "50 : (1,10.0,€60) (2,20.0,€100) (3,30.0,€120)",
                "60 : (1,40.0,€50) (2,30.0,€60) (3,20.0,€70)"
        ));

        // Read test cases from the file
        List<KnapsackProblemInstance> testCases = FileReader.readTestCasesFromFile(tempFile.toString());

        // Check if the test cases were read correctly
        assertEquals(2, testCases.size());
        assertEquals(50, testCases.get(0).maxWeight());
        assertEquals(3, testCases.get(0).itemList().size());
        assertEquals(60, testCases.get(1).maxWeight());
        assertEquals(3, testCases.get(1).itemList().size());
    }

    /**
     * Tests the readTestCasesFromFile method with an invalid file path.
     */
    @Test
    void shouldThrowAPIExceptionWhenFileNotFound() {
        // Try to read test cases from a non-existent file
        assertThrows(APIException.class, () -> FileReader.readTestCasesFromFile("non_existent_file.txt"));
    }

    /**
     * Tests the parseTestCases method with valid input.
     */
    @Test
    void shouldParseTestCasesCorrectly() throws MalformedInputException {
        // Create a Scanner object with test case data
        String data = "50 : (1,10.0,€60) (2,20.0,€100) (3,30.0,€120)\n" +
                "60 : (1,40.0,€50) (2,30.0,€60) (3,20.0,€70)";
        Scanner scanner = new Scanner(data);

        // Parse test cases from the Scanner object
        List<KnapsackProblemInstance> testCases = FileReader.parseTestCases(scanner);

        // Check if the test cases were parsed correctly
        assertEquals(2, testCases.size());
        assertEquals(50, testCases.get(0).maxWeight());
        assertEquals(3, testCases.get(0).itemList().size());
        assertEquals(60, testCases.get(1).maxWeight());
        assertEquals(3, testCases.get(1).itemList().size());
    }

    /**
     * Tests the parseTestCases method with invalid input.
     */
    @Test
    void shouldThrowMalformedInputExceptionWhenInputIsMalformed() {
        // Create a Scanner object with malformed test case data
        String data = "50 : (1,10.0,€60) (2,20.0,€100) 30.0,€120)\n" +  // Missing opening parenthesis
                "60 : 1,40.0,€50) (2,30.0,€60) (3,20.0,€70)";     // Missing opening parenthesis
        Scanner scanner = new Scanner(data);

        // Try to parse test cases from the Scanner object
        assertThrows(MalformedInputException.class , () -> FileReader.parseTestCases(scanner));
    }

    /**
     * Tests the parseTestCase method with valid input.
     */
    @Test
    void shouldParseTestCaseCorrectly() throws MalformedInputException {
        // Create a string with test case data
        String data = "50 : (1,10.0,€60) (2,20.0,€100) (3,30.0,€120)";

        // Parse the test case from the string
        KnapsackProblemInstance testCase = FileReader.parseTestCase(data);

        // Check if the test case was parsed correctly
        assertEquals(50, testCase.maxWeight());
        assertEquals(3, testCase.itemList().size());
    }


    /**
     * Tests the parseTestCase method with invalid input.
     */
    @Test
    void shouldThrowMalformedInputExceptionWhenInputIsMalformedparseTestCase() {
        // Create a string with malformed test case data
        String data = "50 : (1,10.0,€60) 2,20.0,€100) (3,30.0,€120";  // Missing opening parenthesis

        // Try to parse the test case from the string
        assertThrows(MalformedInputException.class , () -> FileReader.parseTestCase(data));
    }


    /**
     * Tests the parseMaxWeight method with valid input.
     */
    @Test
    void shouldParseMaxWeightCorrectly() throws MalformedInputException {
        // Create a string with a valid maximum weight
        String weightStr = "50";

        // Parse the maximum weight from the string
        int maxWeight = FileReader.parseMaxWeight(weightStr);

        // Check if the maximum weight was parsed correctly
        assertEquals(50, maxWeight);
    }

    /**
     * Tests the parseMaxWeight method with invalid input.
     */
    @Test
    void shouldThrowMalformedInputExceptionWhenInputIsInvalid() {
        // Create a string with an invalid maximum weight
        String weightStr = "invalid";

        // Try to parse the maximum weight from the string
        assertThrows(MalformedInputException.class , () -> FileReader.parseMaxWeight(weightStr));
    }

    /**
     * Tests the parseItems method with valid input.
     */
    @Test
    void shouldParseItemsCorrectly() throws MalformedInputException {
        // Create a string with item data
        String itemsStr = "(1,10.0,€60) (2,20.0,€100) (3,30.0,€120)";

        // Parse the items from the string
        List<Item> items = FileReader.parseItems(itemsStr);

        // Check if the items were parsed correctly
        assertEquals(3, items.size());
        assertEquals(1, items.get(0).index());
        assertEquals(10.0, items.get(0).weight());
        assertEquals(60, items.get(0).cost());
        assertEquals(2, items.get(1).index());
        assertEquals(20.0, items.get(1).weight());
        assertEquals(100, items.get(1).cost());
        assertEquals(3, items.get(2).index());
        assertEquals(30.0, items.get(2).weight());
        assertEquals(120, items.get(2).cost());
    }

    /**
     * Tests the parseItems method with invalid input which has missing closing parenthesis.
     */
    @Test
    void shouldThrowMalformedInputExceptionWhenInputMissingClosingParenthesis() {
        // Create a string with malformed item data
        String itemsStr = "(1,10.0,€60) (2,20.0,€100) (3,30.0,€120";  // Missing closing parenthesis

        // Try to parse the items from the string
        assertThrows(MalformedInputException.class , () -> FileReader.parseItems(itemsStr));
    }

    /**
     * TTests the parseItems method with invalid input which has missing closing parenthesis.
     */
    @Test
    void shouldThrowMalformedInputExceptionWhenInputMissingOpenParenthesis() {
        // Create a string with malformed item data
        String itemsStr = "(1,10.0,€60) 2,20.0,€100) (3,30.0,€120)";  // Missing closing parenthesis

        // Try to parse the items from the string
        assertThrows(MalformedInputException.class , () -> FileReader.parseItems(itemsStr));
    }

    /**
     * Tests the parseItem method with valid input.
     */
    @Test
    void shouldParseItemCorrectly() throws MalformedInputException {
        // Create a string with item data
        String itemStr = "(1,10.0,€60)";

        // Parse the item from the string
        Item item = FileReader.parseItem(itemStr);

        // Check if the item was parsed correctly
        assertEquals(1, item.index());
        assertEquals(10.0, item.weight());
        assertEquals(60, item.cost());
    }

    /**
     * Tests the parseItem method with invalid input.
     */
    @Test
    void shouldThrowMalformedInputExceptionWhenInputMissingCurrecySign() {
        // Create a string with malformed item data
        String itemStr = "(1,10.0,60)";  // Missing € symbol

        // Try to parse the item from the string
        assertThrows(MalformedInputException.class , () -> FileReader.parseItem(itemStr));
    }


}
