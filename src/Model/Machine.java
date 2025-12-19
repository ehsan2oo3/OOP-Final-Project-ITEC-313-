package Model;

public class Machine {
    private int ID;
    private String name;
    private double ticketPrice;
    private boolean active;

    public Machine(int ID, String name, double ticketPrice, boolean active){
        this.ID = ID;
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.active = active;
    }

    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public double getTicketPrice(){
        return ticketPrice;
    }
    public boolean isActive(){
        return active;
    }

    public void setName(String name){
        if (name != null && !name.trim().isEmpty()) this.name = name.trim();
    }

    public void setTicketPrice(double ticketPrice){
        if(ticketPrice > 0){
            this.ticketPrice = ticketPrice;
        }
    }

    public void setActive(boolean active){
        this.active = active;
    }

    @Override
    public String toString() {
        return "Machine{id=" + ID + ", name='" + name + "', price=" + ticketPrice + ", active=" + active + "}";
    }
}
