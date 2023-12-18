package h11;

import java.util.List;
import java.util.stream.Collectors;

public class Department {

    private List<Employee> employees;

    public Department(List<Employee> employees) {
        this.employees = employees;
    }


    public List<Employee> getEmployees() {
        return this.employees;
    }

    public List<Position> getListOfJobsInDepartment() {
        return this.employees.stream()
            .map(Employee::getPosition)
            .distinct()
            .collect(Collectors.toList());
    }


    public List<Employee> filterEmployeeByJob(Position position) {
        return this.employees.stream()
            .filter(employee -> employee.getPosition().equals(position))
            .collect(Collectors.toList());
    }

    public int getNumberOfEmployeesByJob(Position position) {
        return this.employees.stream()
            .map(Employee::getPosition)
            .filter(j -> j.equals(position))
            .collect(Collectors.toList())
            .size();
    }
}
