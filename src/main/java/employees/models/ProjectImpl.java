package employees.models;

import employees.contracts.Employee;
import employees.contracts.Project;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProjectImpl implements Project {
    private int id;
    private Set<Employee> employees;

    public ProjectImpl(int id) {
        this.setId(id);
        this.setEmployees(new HashSet<>());
    }

    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public Set<Employee> getEmployees() {
        return Collections.unmodifiableSet(this.employees);
    }

    @Override
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    private void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}