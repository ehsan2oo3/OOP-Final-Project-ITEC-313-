package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private double ticketPrice;
    private boolean active;

    public Long getId() {
        return Id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        if (ticketPrice<=0){
            throw new IllegalArgumentException("Invalid ticket price.");
        }
        this.ticketPrice = ticketPrice;

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format(
                "Machine ID: %d | Name: %s | Ticket Price: %.2f | Status: %s",
                Id,
                name,
                ticketPrice,
                active ? "ACTIVE" : "INACTIVE"
        );
    }
}
