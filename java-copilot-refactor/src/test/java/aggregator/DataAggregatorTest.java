package aggregator;

import config.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataAggregatorTest {

    @TempDir
    Path tempDir;

    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = tempDir.resolve("test.csv").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Product,Price,Quantity\n");
            writer.write("Apple,1.0,10\n");
            writer.write("Banana,0.5,20\n");
            writer.write("Cherry,2.0,5\n");
        }
    }

    @AfterEach
    public void tearDown() {
        tempFile.delete();
    }

    @Test
    public void testReadCSV() {
        DataAggregator aggregator = new DataAggregator();
        List<String[]> data = aggregator.readCSV(tempFile.getAbsolutePath());

        assertNotNull(data, "Data should not be null");
        assertEquals(3, data.size(), "Data size should be 3");
    }

    @Test
    public void testCalculateTotalSales() {
        DataAggregator aggregator = new DataAggregator();
        List<String[]> data = aggregator.readCSV(tempFile.getAbsolutePath());

        double totalSales = aggregator.calculateTotalSales(data);
        assertEquals(25.0, totalSales, "Total sales should be 25.0");
    }

    @Test
    public void testReadCSVWithInvalidFile() {
        DataAggregator aggregator = new DataAggregator();
        assertThrows(IOException.class, () -> {
            aggregator.readCSV("invalid_file.csv");
        });
    }
}