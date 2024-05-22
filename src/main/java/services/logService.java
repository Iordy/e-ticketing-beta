package services;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logService {

    private static final String AUDIT_FILE_PATH = "db_audit_log.csv";

    public void logDatabaseAction(String action, String table) {
        try {
            File file = new File(AUDIT_FILE_PATH);
            System.out.println("am ajuns");
            boolean isNewFile = !file.exists();

            FileWriter fileWriter = new FileWriter(AUDIT_FILE_PATH, true); 
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            if (isNewFile) {
                String[] header = {"Timestamp", "Action", "Table"};
                csvWriter.writeNext(header);
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String[] record = {timestamp, action, table};
            csvWriter.writeNext(record);

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
