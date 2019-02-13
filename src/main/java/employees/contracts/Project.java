package employees.contracts;

import java.util.Set;

public interface Project {
    int getId();

    Set<Employee> getEmployees();

    void addEmployee(Employee employee);
}