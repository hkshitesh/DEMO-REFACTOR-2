package config;

public class Config {
    public static final String CSV_FILE_PATH;

    static {
        String envFilePath = System.getenv("CSV_FILE_PATH");
            CSV_FILE_PATH = envFilePath;
        } else {
            CSV_FILE_PATH = "sales_data.csv";
        }
    }
}
