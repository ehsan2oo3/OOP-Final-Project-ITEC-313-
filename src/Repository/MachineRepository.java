package Repository;

import Model.Machine;

public class MachineRepository {
    private Machine[] data;
    private int size;

    public MachineRepository(int capacity) {
        if (capacity <= 0) capacity = 10;
        data = new Machine[capacity];
        size = 0;
    }

    public int getSize() { return size; }

    public Machine getAt(int index) {
        if (index < 0 || index >= size) return null;
        return data[index];
    }

    public Machine findByID(int ID) {
        for (int i = 0; i < size; i++) {
            if (data[i].getID() == ID) return data[i];
        }
        return null;
    }

    public boolean add(Machine m) {
        if (m == null) return false;
        if (findByID(m.getID()) != null) return false;

        ensureCapacity(size + 1);
        data[size] = m;
        size++;
        return true;
    }

    public void printAll() {
        if (size == 0) {
            System.out.println("(no machines found)");
            return;
        }
        for (int i = 0; i < size; i++) System.out.println(data[i]);
    }

    private void ensureCapacity(int needed) {
        if (needed <= data.length) return;

        int newCap = data.length * 2;
        if (newCap < needed) newCap = needed;

        Machine[] newArr = new Machine[newCap];
        for (int i = 0; i < size; i++) newArr[i] = data[i];
        data = newArr;
    }
}
