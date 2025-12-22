package app;

import model.Employee;
import model.Machine;
import model.Visitor;
import repository.EmployeeRepository;
import services.LunaPark_Services;
import util.HibernateUtil;
import util.Input;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static void printMenu() {
        System.out.println("\n=== LUNA PARK ===");
        System.out.println("1) List Machines");
        System.out.println("2) List Visitors");
        System.out.println("3) List Employees");
        System.out.println("4) Register Visitor");
        System.out.println("5) Register Machine");
        System.out.println("6) Register Employee");
        System.out.println("7) Top-up Visitor Balance");
        System.out.println("8) Activate/Deactivate Machine");
        System.out.println("9) Buy Ride Ticket");
        System.out.println("0) Exit");
    }

    public static void main(String[] args) {

        HibernateUtil.getSessionFactory(); // start once

        try {
            runApp(); // run the menu loop
        } finally {
            HibernateUtil.shutdown(); // always close
        }
    }

    private static void runApp() {
        LunaPark_Services services = new LunaPark_Services();
        EmployeeRepository employeeRepo = new EmployeeRepository();

        Scanner scanner = new Scanner(System.in);
        Input in = new Input(scanner);

        while (true) {
            printMenu();
            int choice = in.readInt("Choose: ");

            switch (choice) {
                case 1 -> {
                    List<Machine> machines = services.getAllMachines();
                    machines.forEach(System.out::println);
                }
                case 2 -> {
                    List<Visitor> visitors = services.getAllVisitors();
                    visitors.forEach(System.out::println);
                }
                case 3 -> employeeRepo.findAll().forEach(System.out::println);

                case 4 -> {
                    String name = in.readNonEmptyString("Enter full name: ");
                    double balance = in.readDouble("Initial balance: ");

                    Visitor v = new Visitor();
                    v.setName(name);
                    v.setBalance(balance);

                    System.out.println(services.registerVisitor(v));
                }

                case 5 -> {
                    String name = in.readNonEmptyString("Machine name: ");
                    double price = in.readDouble("Ticket price: ");
                    boolean active = in.readInt("Active? (1=yes, 0=no): ") == 1;

                    Machine m = new Machine();
                    m.setName(name);
                    m.setTicketPrice(price);
                    m.setActive(active);

                    System.out.println(services.registerMachine(m));
                }

                case 6 -> {
                    String name = in.readNonEmptyString("Employee name: ");
                    String role = in.readNonEmptyString("Employee role: ");
                    double salary = in.readDouble("Employee salary: ");

                    Employee e = new Employee();
                    e.setName(name);
                    e.setRole(role);
                    e.setSalary(salary);

                    boolean ok = employeeRepo.save(e);
                    System.out.println(ok ? "Employee added." : "Failed to add employee.");
                }

                case 7 -> {
                    Long visitorId = Long.parseLong(in.readNonEmptyString("Visitor ID: "));
                    double amount = in.readDouble("Top-up amount: ");
                    System.out.println(services.topUpVisitor(visitorId, amount));
                }

                case 8 -> {
                    Long machineId = Long.parseLong(in.readNonEmptyString("Machine ID: "));
                    boolean active = in.readInt("Set active? (1=yes, 0=no): ") == 1;
                    System.out.println(services.setMachineActive(machineId, active));
                }

                case 9 -> {
                    Long visitorId = Long.parseLong(in.readNonEmptyString("Visitor ID: "));
                    Long machineId = Long.parseLong(in.readNonEmptyString("Machine ID: "));
                    System.out.println(services.BuyRide(visitorId, machineId));
                }

                case 0 -> {
                    System.out.println("Thanks for visiting us. Goodbye.");
                    return; // exits runApp(), then finally shuts down Hibernate
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }
}
