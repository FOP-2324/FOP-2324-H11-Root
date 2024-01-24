package h11;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A {@link Department} manages a list of {@link Employee}s and provides methods to query information about them.
 *
 * @param employees The employees of this department.
 */
public record Department(
    @DoNotTouch List<Employee> employees
) { // not quite sure if we should actually use records here

    /**
     * Returns the employees of this department.
     *
     * @return the employees
     */
    @Override
    public List<Employee> employees() {
        return this.employees;
    }

    /**
     * Returns a {@link List} of occupied {@linkplain Position positions} in this department.
     */
    @StudentImplementationRequired
    public List<Position> getListOfPositionsInDepartment() {
        return this.employees.stream()
            .map(Employee::getPosition)
            .distinct()
            .collect(Collectors.toList());
    }

    /**
     * Returns a {@link List} of all employees with the given {@linkplain Position position}.
     *
     * @param position the position to search for
     * @return the list of employees
     */
    @StudentImplementationRequired
    public List<Employee> filterEmployeeByPosition(Position position) {
        return this.employees.stream()
            .filter(employee -> employee.getPosition().equals(position))
            .collect(Collectors.toList());
    }

    /**
     * Returns the number of employees with the given {@linkplain Position position}.
     *
     * @param position the position to search for
     * @return the number of employees
     */
    @StudentImplementationRequired
    public long getNumberOfEmployeesBySalary(double salary) {
        return this.employees.stream()
            .map(Employee::getSalary)
            .filter(eSalary -> eSalary >= salary)
            .count();
    }

    /**
     * Increases the salary of all employees by the given amount.
     *
     * @param amount the amount to increase the salary by
     */
    @StudentImplementationRequired
    public void adjustSalary(double amount, boolean increase) {
        this.employees.forEach(employee -> employee.setSalary(
            employee.getSalary() + (increase ? amount : -amount)));
    }
}
