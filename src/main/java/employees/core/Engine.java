package employees.core;

import employees.contracts.Employee;
import employees.contracts.Project;
import employees.io.ConsoleWriter;
import employees.io.FromFileReader;
import employees.io.Reader;
import employees.io.Writer;
import employees.models.EmployeeImpl;
import employees.repository.ProjectsRepository;
import employees.utils.DateUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Engine implements Runnable {
    private static final String FILE_PATH = "src/main/java/employees/input/file.txt";

    private Reader reader;
    private Writer writer;
    private ProjectsRepository repository;

    Engine() throws FileNotFoundException {
        this.writer = new ConsoleWriter();
        this.reader = new FromFileReader(new File(FILE_PATH));
        this.repository = new ProjectsRepository();
    }

    @Override
    public void run() {
        String line = null;

        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (line != null) {
            String[] args = line.split(", ");

            int employeeId = Integer.parseInt(args[0]);
            int projectId = Integer.parseInt(args[1]);
            LocalDate dateFrom = DateUtils.getDateFromString(args[2]);
            LocalDate dateTo = DateUtils.getDateFromString(args[3]);

            Employee employee = new EmployeeImpl(employeeId, dateFrom, dateTo);
            this.repository.addEmployeeToProject(projectId, employee);

            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Map<Integer, Map<Integer, Long>> employeeColleaguesDaysTogether = new HashMap<>();

        int[] pairWhoWorkedTogetherForLongest = new int[2];
        long mostTimeTogether = Long.MIN_VALUE;

        for (Project project : this.repository.getAllProjects()) {
            Set<Employee> employeesOnProject = project.getEmployees();

            for (Employee employee : employeesOnProject) {
                employeeColleaguesDaysTogether.putIfAbsent(employee.getId(), new HashMap<>());
                Map<Integer, Long> colleaguesTimeTogether = employeeColleaguesDaysTogether.get(employee.getId());

                for (Employee colleague : employeesOnProject) {
                    if (employee == colleague) {
                        continue;
                    }

                    long daysWorkedTogether = DateUtils.getNumberOfDaysIntersecting(
                            employee.getDateFrom(),
                            employee.getDateTo(),
                            colleague.getDateFrom(),
                            colleague.getDateTo());

                    if (colleaguesTimeTogether.containsKey(colleague.getId())) {
                        daysWorkedTogether = colleaguesTimeTogether.get(colleague.getId()) + daysWorkedTogether;
                    }

                    colleaguesTimeTogether.put(colleague.getId(), daysWorkedTogether);

                    if (daysWorkedTogether > mostTimeTogether) {
                        mostTimeTogether = daysWorkedTogether;

                        pairWhoWorkedTogetherForLongest[0] = employee.getId();
                        pairWhoWorkedTogetherForLongest[1] = colleague.getId();
                    }
                }
            }
        }

        this.writer.writeLine(String.format("Employee with id: %d and employee with id: %d have worked for the most time together: %d days.",
                pairWhoWorkedTogetherForLongest[0], pairWhoWorkedTogetherForLongest[1], mostTimeTogether));
    }
}