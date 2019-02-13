package employees.models;

import employees.contracts.Employee;

import java.time.LocalDate;

public class EmployeeImpl implements Employee {
    private int id;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public EmployeeImpl(int id, LocalDate dateFrom, LocalDate dateTo) {
        this.setId(id);

        if (dateTo.isBefore(dateFrom)) {
            throw new IllegalArgumentException("End date can not be before start date!");
        }

        this.setDateFrom(dateFrom);
        this.setDateTo(dateTo);
    }

    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public LocalDate getDateFrom() {
        return this.dateFrom;
    }

    private void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Override
    public LocalDate getDateTo() {
        return this.dateTo;
    }

    private void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}