package integrationtest;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests for the Packer class, ensuring correct packing of items
 * based on input files.
 */
class PackerIntegrationTest {

    /**
     * Tests the Packer.pack method to ensure it correctly packs items according
     * to the input file and produces the expected output.
     *
     * @throws APIException If there is an API exception during packing.
     * @throws IOException  If there is an IO exception when reading input or
     *                      expected output files.
     */
    @Test
    void shouldPackItemsCorrectlyGivenInputFile() throws APIException, IOException {
        // Input file path for testing
        String inputFilePath = Paths.get("src", "test", "resources", "example_input").toString();
        // Expected output file path for testing
        String outputFilePath = Paths.get("src", "test", "resources", "example_output").toString();

        // Read the expected output from the output file
        String expectedOutput = new String(Files.readAllBytes(Paths.get(outputFilePath))).trim();

        // Get the actual output by invoking the Packer.pack method with the input file
        String actualOutput = Packer.pack(inputFilePath);

        // Compare the expected and actual output to validate correctness
        assertEquals(expectedOutput, actualOutput);
    }
}
