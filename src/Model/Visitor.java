package Model;

public class Visitor extends Person{
    private double balance;

    public Visitor(int ID, String fullName, double balance){
        super(ID, fullName);
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public void addBalance(double amount){
        if (amount > 0){
            balance += amount;
        }else{
            System.out.println("Amount is Incorrect");
        }
    }

    public boolean charge(double amount){
        if(amount <= 0 || balance < amount){
            return false;
        }else{
            balance -= amount;
            return true;
        }
    }

    @Override
    public String toString() {
        return "Visitor{id=" + getID() +
                ", name='" + getFullName() +
                "', balance=" + balance + "}";
    }


}
