package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name="visitors")
public class Visitor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private double balance;

    public Long getId() {
        return Id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addBalance(double amount){
        if (amount > 0){
            balance += amount;
        }else{
            throw new IllegalArgumentException("Amount is Incorrect");
        }
    }

    public void charge(double amount){
        if(amount <= 0 || balance < amount){
            throw new IllegalArgumentException("Invalid amount or not enough balance.");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format(
                "Visitor ID: %d | Name: %s | Balance: %.2f",
                getId(),
                getName(),
                balance
        );
    }



}
