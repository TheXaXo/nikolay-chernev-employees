package employees.repository;

import employees.contracts.Employee;
import employees.contracts.Project;
import employees.models.ProjectImpl;

import java.util.*;

public class ProjectsRepository {
    private Map<Integer, Project> idProject;

    public ProjectsRepository() {
        this.idProject = new HashMap<>();
    }

    public void addEmployeeToProject(int projectId, Employee employee) {
        if (!this.idProject.containsKey(projectId)) {
            this.idProject.put(projectId, new ProjectImpl(projectId));
        }

        this.idProject.get(projectId).addEmployee(employee);
    }

    public Collection<Project> getAllProjects() {
        return this.idProject.values();
    }
}