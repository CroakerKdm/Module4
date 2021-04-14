package FilesManagment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Croaker
 * @version 1.0.0
 * @project Module4
 * @class LogManager
 * @since 11.04.2021 - 15.56
 **/

public class LogManager {
    private String file;

    public LogManager() {
    }

    public LogManager(String file) {
        this.file = file;
    }

    public static StringBuilder logsByDate(String file, String date) throws IOException {
        List<String> list = Files.lines(Paths.get(file))
                .filter(log -> log.contains("2019"))
                .filter(log -> log.contains("ERROR"))
                .collect(Collectors.toList());

        StringBuilder str = new StringBuilder();
        for (String log : list) {
            str.append(log).append('\n');
        }
        return str;
    }

    public static void logsByDateToFile(String file, LocalDate date) throws IOException {
        String dateAsString = date.toString();
        String yearAndMonthAsString = dateAsString.substring(0, dateAsString.length() - 3);

        StringBuilder list = logsByDate(file, yearAndMonthAsString);
        String fileOutput = "ERROR_" + yearAndMonthAsString + ".log";
        Files.writeString(Paths.get(fileOutput), list.toString());
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
