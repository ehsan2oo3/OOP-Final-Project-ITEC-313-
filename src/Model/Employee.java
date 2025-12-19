package Model;

public class Employee extends Person{
    private String role;
    private double salary;

    public Employee(int ID, String fullName, String role, double salary){
        super(ID, fullName);
        this.role = role;
        this.salary = salary;
    }

    public String getRole(){
        return role;
    }
    public double getSalary(){
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + getID() +
                ", name='" + getFullName() +
                "', role='" + role +
                "', salary=" + salary + "}";
    }
}
