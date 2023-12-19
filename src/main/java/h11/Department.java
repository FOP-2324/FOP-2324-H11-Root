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
            .toList();
    }

    public List<Employee> filterEmployeeByPosition(Position position) {
        return this.employees.stream()
            .filter(employee -> employee.getPosition().equals(position))
            .toList();
    }

    public long getNumberOfEmployeesBySalary(double salary) {
        return this.employees.stream()
            .map(Employee::getSalary)
            .filter(s -> s >= salary)
            .count();
    }

    public void adjustSalary(double amount, boolean increase) {
        this.employees.forEach(employee -> employee.setSalary(
            employee.getSalary() + (increase ? amount : -amount)
        ));
    }
}
