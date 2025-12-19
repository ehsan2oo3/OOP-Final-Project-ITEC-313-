package Services;

import Model.Machine;
import Model.Visitor;
import Repository.MachineRepository;
import Repository.VisitorRepository;

public class LunaPark_Services {
    private final VisitorRepository visitorRepo;
    private final MachineRepository machineRepo;

    public LunaPark_Services(VisitorRepository visitorRepo, MachineRepository machineRepo){
        this.visitorRepo = visitorRepo;
        this.machineRepo = machineRepo;
    }

    public String topUpVisitor(int VisitorID, int amount){
        Visitor v = visitorRepo.findByID(VisitorID);
        if(v == null) return "Visitor not found";
        if(amount <= 0) return "Amount must be greater than 0";
        v.addBalance(amount);
        return "TopUp successful. new balance: " + v.getBalance();
    }

    public String setMachineActive(int machineID, boolean active){
        Machine m = machineRepo.findByID(machineID);
        if(m == null) return "Machine not found";
        m.setActive(active);
        return "Machine Updated: " + m;
    }

    public String BuyRide(int visitorID, int machineID){
        Visitor v = visitorRepo.findByID(visitorID);
        if(v == null) return "Visitor not found";

        Machine m = machineRepo.findByID(machineID);
        if(m == null) return "Machine not found";

        if(!m.isActive()) return "Machine is not active currently";

        double price = m.getTicketPrice();
        if(!v.charge(price)){
            return "Insufficient balance. Price=" + price + ", balance=" + v.getBalance();
        }
        return "Ticket purchased. Visitor now rides '" + m.getName() + "'. Remaining balance: " + v.getBalance();
    }

}
