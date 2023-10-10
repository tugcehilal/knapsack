package com.mobiquity.file;

import com.mobiquity.exception.*;
import com.mobiquity.model.Item;
import com.mobiquity.model.KnapsackProblemInstance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//test

/**
 * This class is responsible for reading and parsing test cases from a file.
 */
public class FileReader {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private FileReader() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Reads test cases from a file.
     *
     * @param filePath The path to the file containing the test cases.
     * @return A list of KnapsackProblemInstance objects representing the test cases.
     * @throws APIException If an error occurs while reading the file or parsing the test cases.
     */
    public static List<KnapsackProblemInstance> readTestCasesFromFile(String filePath) throws APIException {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            return parseTestCases(scanner);
        } catch (FileNotFoundException e) {
            throw new FileReadingException("File not found", e);
        }
    }

    /**
     * Parses test cases from a Scanner object.
     *
     * @param scanner The Scanner object to read the test cases from.
     * @return A list of KnapsackProblemInstance objects representing the test cases.
     * @throws MalformedInputException If a line in the input is malformed.
     */
    public static List<KnapsackProblemInstance> parseTestCases(Scanner scanner) throws MalformedInputException {
        List<KnapsackProblemInstance> testCases = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            testCases.add(parseTestCase(line));
        }
        return testCases;
    }

    /**
     * Parses a single test case from a string.
     *
     * @param line The string containing the test case data.
     * @return A KnapsackProblemInstance object representing the test case.
     * @throws MalformedInputException If the string is not in the expected format.
     */
    public static KnapsackProblemInstance parseTestCase(String line) throws MalformedInputException {
        String[] parts = line.split(":");
        if (parts.length != 2) {
            throw new MalformedInputException("Malformed line: " + line);
        }
        int maxWeight = parseMaxWeight(parts[0].trim());
        List<Item> itemList = parseItems(parts[1].trim());
        return new KnapsackProblemInstance(maxWeight, itemList);
    }

    /**
     * Parses the maximum weight from a string.
     *
     * @param weightStr The string containing the maximum weight.
     * @return The maximum weight as an integer.
     * @throws MalformedInputException If the string does not contain a valid integer.
     */
    public static int parseMaxWeight(String weightStr) throws MalformedInputException {
        try {
            return Integer.parseInt(weightStr);
        } catch (NumberFormatException e) {
            throw new MalformedInputException("Invalid weight: " + weightStr, e);
        }
    }

    /**
     * Parses a list of items from a string.
     *
     * @param itemsStr The string containing the item data.
     * @return A list of Item objects representing the items.
     * @throws MalformedInputException If an item in the string is not in the expected format.
     */
    public static List<Item> parseItems(String itemsStr) throws MalformedInputException {
        String[] itemParts = itemsStr.split("\\s+");
        List<Item> items = new ArrayList<>();
        for (String itemPart : itemParts) {
            if (!itemPart.startsWith("(") || !itemPart.endsWith(")")) {
                throw new MalformedInputException("Missing parenthesis in item: " + itemPart);
            }
            Item item = parseItem(itemPart);
            items.add(item);
        }
        return items;
    }


    /**
     * Parses an item from a string.
     *
     * @param itemStr The string containing the item data.
     * @return An Item object representing the item.
     * @throws MalformedInputException If the string is not in the expected format for an item.
     */
    public static Item parseItem(String itemStr) throws MalformedInputException {
        if (!itemStr.contains("€")) {
            throw new MalformedInputException("Missing currency symbol in item: " + itemStr);
        }
        itemStr = itemStr.replaceAll("[()€]", "");
        String[] itemParts = itemStr.split(",");
        if (itemParts.length != 3) {
            throw new MalformedInputException("Malformed item: " + itemStr);
        }
        int index;
        double weight;
        int cost;
        try {
            index = Integer.parseInt(itemParts[0]);
            weight = Double.parseDouble(itemParts[1]);
            cost = Integer.parseInt(itemParts[2]);
        } catch (NumberFormatException e) {
            throw new MalformedInputException("Invalid item data: " + Arrays.toString(itemParts), e);
        }
        return new Item(index, weight, cost);
    }

}
