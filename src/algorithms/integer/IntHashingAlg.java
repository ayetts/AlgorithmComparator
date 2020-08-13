package algorithms.integer;
import java.io.*;
import support.*;

public class IntHashingAlg {
    private final IntLinkedList[] table;
    private final int size;
    private int compCount = 0;
    
    public IntHashingAlg(int size, String order) {
        this.table = new IntLinkedList[size];
        this.size = size;
        initTable(order);
    }
    
    private void initTable(String order) {
        String fileName = "";
        
        switch (order)  {
            case "Inorder":
                fileName = "textfiles/InI" + this.size + ".txt";
                break;
                
            case "Reverse":
                fileName = "textfiles/RevI" + this.size + ".txt";
                break;
                
            case "Random":
                fileName = "textfiles/RandI" + this.size + ".txt";
                break;
                
            default:
                break;
        }
        
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < this.size; i++) {
                int num = Integer.parseInt(in.readLine());
                add(num);
            }
        } catch (IOException e) {
            System.err.println(e);
        }            
    }
    
    @Override
    public String toString() {
        String str = "";
        IntDLLNode node;
        int counter = 0;
        
        for (int i = 0; i < this.size; i++) {
            if (this.table[i] != null) {
                node = this.table[i].getFirstNode();
            } else {
                continue;
            }
            
            while (node != null) {
                counter++;
                str += node.getData() + " ";
                node = node.getNext();
                
                if (counter == 2) {
                    counter = 0;
                    str += "\n";
                }
            }
        }
        
        return str;
    }
    
    public boolean search(int element) {
        int index = hash(element);
        if (this.table[index] == null) {
            return false;
        }
        
        IntDLLNode node = this.table[index].getFirstNode();
        
        while (node != null) {
            this.compCount++;
            
            if (node.getData() == element) {
                return true;
            } else {
                node = node.getNext();
            }
        }
        
        return false;
    }
    
    public void add(int element) {
        int index = hash(element);
        
        if (this.table[index] == null) {
            this.table[index] = new IntLinkedList();
        }
        
        this.table[index].addFront(element);
    }
    
    private int hash(int element) {
        return Math.abs(element % this.size);
    }
    
    public int getCompCount() {
        return this.compCount;
    }
}
