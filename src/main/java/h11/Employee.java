package h11;

public class Employee {

    private final String NAME;
    private int salary;
    private Position position;

    public Employee(String name, Position position, int salary) {
        this.NAME = name;
        this.position = position;
        this.salary = salary;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getFirstName() {
        return this.NAME.split(" ")[0];
    }

    public String getLastName() {
        return this.NAME.split(" ")[1];
    }
}
