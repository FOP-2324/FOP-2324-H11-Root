package h11;

import java.util.List;
import java.util.stream.Collectors;

public class Department {

    private List<Employee> employees;

    public Department(List<Employee> employees) {
        this.employees = employees;
    }


    public List<Employee> filterEmployeeByJob(Job job) {
        return this.employees.stream()
            .filter(employee -> employee.getJob().equals(job))
            .collect(Collectors.toList());
    }
}
