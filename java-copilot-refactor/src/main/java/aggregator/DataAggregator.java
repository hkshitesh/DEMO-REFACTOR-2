package aggregator;

import config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAggregator {
    private static final Logger logger = LoggerFactory.getLogger(DataAggregator.class);

    public List<String[]> readCSV() {
        List<String[]> data = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(Config.CSV_FILE_PATH))) {
            while ((line = br.readLine()) != null) {
                try {
                    String[] row = line.split(csvSplitBy);
                    data.add(row);
                } catch (NumberFormatException e) {
                    logger.error("Error parsing line: " + line, e);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading file: " + Config.CSV_FILE_PATH, e);
        }

        logger.info("CSV file read successfully with {} rows", data.size());
        return data;
    }

    // Add your business logic methods here
}