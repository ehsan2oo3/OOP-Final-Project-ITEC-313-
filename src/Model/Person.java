package Model;

public abstract class Person {
    private int ID;
    private String fullName;

    public Person (int ID, String FullName){
        this.ID = ID;
        this.fullName = fullName;
    }

    public int getID(){
        return ID;
    }

    public String getFullName(){
        return fullName;
    }

    @Override
    public String toString(){
        return "ID: " + ID + ", Full Name: " + fullName;
    }
}
