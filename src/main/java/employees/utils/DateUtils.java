package employees.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static LocalDate getDateFromString(String string) {
        if (string.equals("NULL")) {
            return LocalDate.now();
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(string, format);
    }

    public static long getNumberOfDaysIntersecting(LocalDate startDateEmp1, LocalDate endDateEmp1, LocalDate startDateEmp2, LocalDate endDateEmp2) {
        if (startDateEmp1.isAfter(endDateEmp2) || endDateEmp1.isBefore(startDateEmp2)) {
            return 0;
        }

        LocalDate laterStart = startDateEmp1.isAfter(startDateEmp2) ? startDateEmp1 : startDateEmp2;
        LocalDate earlierEnd = endDateEmp1.isBefore(endDateEmp2) ? endDateEmp1 : endDateEmp2;

        return ChronoUnit.DAYS.between(laterStart, earlierEnd);
    }
}