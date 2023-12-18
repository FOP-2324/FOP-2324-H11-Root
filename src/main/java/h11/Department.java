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

    public List<Position> getListOfPositionsInDepartment() {
        return this.employees.stream()
            .map(Employee::getPosition)
            .distinct()
            .collect(Collectors.toList());
    }

    public List<Employee> filterEmployeeByPosition(Position position) {
        return this.employees.stream()
            .filter(employee -> employee.getPosition().equals(position))
            .collect(Collectors.toList());
    }

    public long getNumberOfEmployeesByPosition(Position position) {
        return this.employees.stream()
            .map(Employee::getPosition)
            .filter(p -> p.equals(position))
            .count();
    }

    public void increaseSalary(double amount) {
        this.employees.forEach(employee -> employee.setSalary(employee.getSalary() + amount));
    }
}
