package FilesManagment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

/**
 * @author Croaker
 * @version 1.0.0
 * @project Module 4
 * @class Main
 * @since 11.04.2021 - 13.38
 **/

public class Main {
    public static void main(String[] args) throws IOException {
        LocalDateTime start = LocalDateTime.now();
        String logs1 = new String(Files.readAllBytes(Paths.get("logs.txt")));
        int errorCounter = 0;

        int indexOfFirst2020 = logs1.indexOf("2020-"); //logs might contain more then one line, incoming string is split in two separate for 2019 and 2020
        String logs2020 = logs1.substring(indexOfFirst2020); //this two substring are split by year, which is present at the start of every log,
        String logs2019 = logs1.substring(0, indexOfFirst2020); //rather then by lines

        String[] logsSplit2019 = logs2019.split("\n2019-"); //dates might be found inside the logs, this way,
        String[] logsSplit2020 = logs2020.split("\n2020-"); //only the dates at the start of the sentences are calculated

        Object[] logs = Stream.of(logsSplit2019, logsSplit2020).flatMap(Stream::of).toArray();

        for (Object log : logs) {
            if (log.toString().contains("ERROR")) {
                errorCounter++;
            }
        }
        LocalDateTime finish = LocalDateTime.now();

        System.out.println("Total number of logs = " + logs.length);
        System.out.println("Total number of errors = " + errorCounter);
        System.out.println("Time first method = " + (int) ChronoUnit.MILLIS.between(start, finish));

        start = LocalDateTime.now();
        System.out.println("\nTotal number of errors = " + Files.readAllLines(Paths.get("logs.txt"))
                .stream().filter(line -> line.contains("ERROR")).count());
        finish = LocalDateTime.now();
        System.out.println("Time second method = " + (int) ChronoUnit.MILLIS.between(start, finish));
    }
}

/*
    Total number of logs = 2494727
    Total number of errors = 361
    Time first method = 4310

    Total number of errors = 361
    Time second method = 1882
*/

/*
number of logs:
2845598 split by \n
2504926 split by 2019- or 2020-
2494727 split by \n2019- or \n2020-
*/
