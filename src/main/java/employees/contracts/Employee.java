package employees.contracts;

import java.time.LocalDate;

public interface Employee {
    int getId();

    LocalDate getDateFrom();

    LocalDate getDateTo();
}