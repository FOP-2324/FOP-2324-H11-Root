package h11;

public class Employee {

    private final String NAME;
    private int salary;
    private Job job;

    public Employee(String name, Job job, int salary) {
        this.NAME = name;
        this.job = job;
        this.salary = salary;
    }

    public Job getJob() {
        return this.job;
    }

    public int getSalary() {
        return this.salary;
    }

    public String getFirstName() {
        return this.NAME.split(" ")[0];
    }

    public String getLastName() {
        return this.NAME.split(" ")[1];
    }
}
