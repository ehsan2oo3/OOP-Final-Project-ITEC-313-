package Repository;

import Model.Visitor;

public class VisitorRepository {
    private Visitor[] data;
    private int size;

    public VisitorRepository(int capacity) {
        if (capacity <= 0) capacity = 10;
        data = new Visitor[capacity];
        size = 0;
    }

    public int getSize(){
        return size;
    }
    public Visitor getAt(int index){
        if(index < 0 || index >= size){
            return null;
        }else{
            return data[index];
        }
    }

    public Visitor findByID(int id){
        for(int i = 0; i < size; i++){
            if(data[i].getID() == id) return data[i];
        }
        return null;
    }


    public boolean add(Visitor v) {
        if (v == null) return false;
        if (findByID(v.getID()) != null) return false;

        ensureCapacity(size + 1);
        data[size] = v;
        size++;
        return true;
    }

    public boolean removeById(int id) {
        int idx = indexOf(id);
        if (idx == -1) return false;

        for (int i = idx; i < size - 1; i++) data[i] = data[i + 1];
        data[size - 1] = null;
        size--;
        return true;
    }

    public void printAll(){
        if(size == 0){
            System.out.println("No Visitors found");
            return;
        }
        for(int i = 0; i < size; i++){
            System.out.println(data[i]);
        }
    }

    private int indexOf(int id){
        for(int i = 0; i < size; i++){
            if(data[i].getID() == id){
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity(int needed){
        if(needed <= data.length) return;

        int newCap = data.length * 2;
        if(newCap < needed) newCap = needed;

        Visitor[] newArr = new Visitor[newCap];
        for(int i = 0; i < size; i++) newArr[i] = data[i];
        data = newArr;
    }
}
