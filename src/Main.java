import Model.Employee;
import Model.Machine;
import Model.Visitor;
import Repository.EmployeeRepository;
import Repository.MachineRepository;
import Repository.VisitorRepository;
import Services.LunaPark_Services;
import Util.Input;

import java.util.Scanner;

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
private static void seed(VisitorRepository visitorRepo, MachineRepository machineRepo, EmployeeRepository employeeRepo) {
    visitorRepo.add(new Visitor(1, "Ehsan", 100));
    visitorRepo.add(new Visitor(2, "Alex", 20));

    machineRepo.add(new Machine(1, "Ferris Wheel", 15, true));
    machineRepo.add(new Machine(2, "Bumper Cars", 25, true));
    machineRepo.add(new Machine(3, "Roller Coaster", 50, false));

    employeeRepo.add(new Employee(1, "Sam Worker", "Operator", 1200));
    employeeRepo.add(new Employee(2, "Lina Tech", "Technician", 1400));
}

void main() {
    VisitorRepository visitorRepo = new VisitorRepository(10);
    MachineRepository machineRepo = new MachineRepository(10);
    EmployeeRepository employeeRepo = new EmployeeRepository(10);

    LunaPark_Services services = new LunaPark_Services(visitorRepo, machineRepo);

    seed(visitorRepo, machineRepo, employeeRepo);

    Scanner scanner = new Scanner(System.in);
    Input in = new Input(scanner);

    while(true){
        printMenu();
        int choice = in.readInt("Choose: ");

        switch(choice){
            case 1-> machineRepo.printAll();
            case 2-> visitorRepo.printAll();
            case 3-> employeeRepo.printAll();

            case 4->{ // Visitor Registration panel
                int ID = in.readInt("Enter ID: ");
                String name = in.readNonEmptyString("Enter your full name: ");
                double balance = in.readDouble("Initial balance: ");
                boolean ok = visitorRepo.add(new Visitor(ID, name, balance));
                System.out.println(ok ? "Visitor added." : "Failed (maybe duplicate ID).");
            }

            case 5-> { // Enter Machine
                int ID = in.readInt("Machine ID: ");
                String name = in.readNonEmptyString("Enter the Machine name: ");
                double price = in.readDouble("Ticket price: ");
                int a = in.readInt("IS it Active? (1-YES, 0-NO)");
                boolean active = (a == 1);
                boolean ok = machineRepo.add(new Machine(ID, name, price, active));
                System.out.println(ok ? "Machine added." : "Failed (maybe duplicate ID).");
            }

            case 6->{ //Employee Registration
                int ID = in.readInt("Employee ID: ");
                String name = in.readNonEmptyString("Enter Employee's name: ");
                String role = in.readNonEmptyString("Employee's Role: ");
                double salary = in.readDouble("Employee's Salary: ");
                boolean ok = employeeRepo.add(new Employee(ID, name, role, salary));
                System.out.println(ok ? "Employee added." : "Failed (maybe duplicate ID).");
            }

            case 7 -> { // Top-up
                int VisitrID = in.readInt("Visitor ID: ");
                double amount = in.readDouble("Top-up amount: ");
                System.out.println(services.topUpVisitor(VisitrID,(int) amount));
            }

            case 8 -> { // Activate/Deactivate
                int MachinID = in.readInt("Machine ID: ");
                int a = in.readInt("Set active? (1=yes, 0=no): ");
                boolean active = (a == 1);
                System.out.println(services.setMachineActive(MachinID, active));
            }

            case 9 -> { // Buy Ride
                int VisitorID = in.readInt("Visitor ID: ");
                int MachineID = in.readInt("Machine ID: ");
                System.out.println(services.BuyRide(VisitorID, MachineID));
            }
            case 0 -> {
                System.out.println("Thanks for visiting us. Goodbye.");
                return;
            }
            default -> System.out.println("Invalid option.");
        }
    }
}

