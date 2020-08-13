package algorithms.string;
import java.io.*;
import support.*;

public class StrHashingAlg {
    private final LinkedList<String>[] table;
    private final int size;
    private int compCount = 0;
    
    public StrHashingAlg(int size, String order) {
        this.table = new LinkedList[size];
        this.size = size;
        initTable(order);
    }
    
    private void initTable(String order) {
        String fileName = "";
        
        switch (order)  {
            case "Inorder":
                fileName = "textfiles/InS" + this.size + ".txt";
                break;
                
            case "Reverse":
                fileName = "textfiles/RevS" + this.size + ".txt";
                break;
                
            case "Random":
                fileName = "textfiles/RandS" + this.size + ".txt";
                break;
                
            default:
                break;
        }
        
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < this.size; i++) {
                add(in.readLine());
            }
        } catch (IOException e) {
            System.err.println(e);
        }            
    }
    
    @Override
    public String toString() {
        DLLNode node;
        int counter = 0;
        String str = "";
        
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

    public boolean search(String element) {
        int index = hash(element);
        
        if (this.table[index] == null) {
            this.table[index] = new LinkedList<>();
        }
        
        DLLNode node = this.table[index].getFirstNode();
        
        while (node != null) {
            this.compCount++;
            
            if (node.getData().compareTo(element) == 0) {
                return true;
            } else {
                node = node.getNext();
            }
        }
        
        return false;
    }
    
    public void add(String element) {
        int index = hash(element);
        
        if (this.table[index] == null) {
            this.table[index] = new LinkedList<>();
        }
        
        this.table[index].addFront(element);
    }
    
    private int hash(String element) {
        return Math.abs(element.hashCode() % this.size);
    }
    
    public int getCompCount() {
        return this.compCount;
    }
}
