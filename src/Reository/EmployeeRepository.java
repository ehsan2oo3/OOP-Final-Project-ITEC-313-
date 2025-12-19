package Reository;

import Model.Employee;

public class EmployeeRepository {
    private Employee[] data;
    private int size;

    public EmployeeRepository(int capacity){
        if(capacity <= 0) capacity = 10;
        data = new Employee[capacity];
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public Employee getAt(int index){
        if(index < 0 || index >= size) return null;
        return data[index];
    }

    public Employee findByID(int ID){
        for(int i = 0; i < size; i++){
            if(data[i].getID() == ID) return data[i];
        }
        return null;
    }

    public boolean add(Employee e){
        if(e == null)return false;
        if(findByID(e.getID()) != null) return false;

        ensureCapacity(size + 1);
        data[size] = e;
        size++;
        return true;
    }

    public void printAll(){
        if(size == 0){
            System.out.println("No employees found");
            return;
        }
        for(int i = 0; i < size; i++){
            System.out.println(data[i]);
        }
    }

    private void ensureCapacity(int needed) {
        if (needed <= data.length) return;

        int newCap = data.length * 2;
        if (newCap < needed) newCap = needed;

        Employee[] newArr = new Employee[newCap];
        for (int i = 0; i < size; i++) newArr[i] = data[i];
        data = newArr;
    }
}
