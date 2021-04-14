package FilesManagment;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author Croaker
 * @version 1.0.0
 * @project Module4
 * @class Main
 * @since 11.04.2021 - 15.49
 **/

public class Main {
    public static void main(String[] args) throws IOException {
        LocalDate[] dates = new LocalDate[]{
                LocalDate.of(2020, Month.JANUARY, 1),
                LocalDate.of(2020, Month.FEBRUARY, 1),
                LocalDate.of(2020, Month.MARCH, 1),
                LocalDate.of(2019, Month.NOVEMBER, 1),
                LocalDate.of(2019, Month.DECEMBER, 1),
        };

        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < 5; i++) {
            LogManager.logsByDateToFile("logs.txt", dates[i]);
        }
        LocalDateTime finish = LocalDateTime.now();

        System.out.println("Time to process 5 month in consistent way = " + ChronoUnit.MILLIS.between(start, finish) + "\n");

        for (int i = 0; i < 5; i++) {
            new MyThread(dates[i], "logs.txt").start();
        }
    }
}

/*
        Error logs are made by month instead of days, because there only few month with actual errors

        Time to process 5 month in consistent way = 6345

        Thread-2 has been started at 2021-04-14T21:16:20.395476
        Thread-3 has been started at 2021-04-14T21:16:20.396476400
        Thread-1 has been started at 2021-04-14T21:16:20.395476
        Thread-0 has been started at 2021-04-14T21:16:20.395476
        Thread-4 has been started at 2021-04-14T21:16:20.396476400
        Thread-2 has been finished at 2021-04-14T21:16:22.196884900
        Thread-2 DURATION is  1801
        Thread-3 has been finished at 2021-04-14T21:16:22.206881400
        Thread-3 DURATION is  1810
        Thread-1 has been finished at 2021-04-14T21:16:22.270860500
        Thread-1 DURATION is  1875
        Thread-4 has been finished at 2021-04-14T21:16:22.345837500
        Thread-4 DURATION is  1949
        Thread-0 has been finished at 2021-04-14T21:16:22.376827900
        Thread-0 DURATION is  1981

        Longest thread took 3 times less time, than processing files in a consistent way
*/

