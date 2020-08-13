package algorithms.integer;
import java.io.*;
import support.*;

public class IntRefAlgs {
    private final IntLinkedList list;
    private final int size;
    private int compCount = 0;
    
    public IntRefAlgs(int size, String order) {
        this.list = new IntLinkedList();
        
        if (size == 50 || size == 500 || size == 5000) {
            this.size = size;
        } else {
            System.err.println("File size not available.");
            this.size = 50;
        }
                
        initValues(order);
    }
    
    private void initValues(String order) {   
        String fileName = "";
        
        switch (order) {
            case "Inorder":
                fileName = "textfiles/RevI" + this.size + ".txt"; //adds in reverse so "Rev"
                break;
                
            case "Reverse":
                fileName = "textfiles/InI" + this.size + ".txt"; //adds in reverse so "In"
                break;
                
            case "Random":
                fileName = "textfiles/RandI" + this.size + ".txt";
                break;
                
            default:
                break;
        }
            
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < this.size; i++) {
                this.list.addFront(Integer.parseInt(in.readLine()));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    @Override
    public String toString() {
        IntDLLNode hold = this.list.getFirstNode();
        String str = "";
        
        while (hold != null) {
            for (int i = 0; i < 2; i++) {
                if (hold != null) {
                    str += hold.getData() + " ";
                    hold = hold.getNext();
                }
            }
            
            str += "\n";
        }
        
        return str;
    }
    
    public int getCompCount() {
        return this.compCount;
    }
    
    // Bubble Sort ***************************************************** //
    private void bubbleUp(IntDLLNode start, int end) {
        for (int i = 1; i < end; i++) {
            this.compCount++;
            
            if (start.getData() > start.getNext().getData()) {
                swap(start, start.getNext());
            }
            
            start = start.getNext();
        }
    }
 
    public void bubbleSort() {
        for (int i = 0; i < this.size; i++) {
            bubbleUp(this.list.getFirstNode(), this.size - i);
        }
    }
    
    // Insertion Sort ************************************************** //    
    private void insertItem(IntDLLNode start, IntDLLNode current) {
        while (current != start) {
            IntDLLNode item = current.getPrev();
            this.compCount++;
            
            if (current.getData() < item.getData()) {
                swap(current, item);
            }
            
            current = current.getPrev();
        }
    }
 
    public void insertionSort() {
        IntDLLNode current = this.list.getFirstNode();
        
        while (current != null) {
            insertItem(this.list.getFirstNode(), current);
            current = current.getNext();
        }
    }
    
    // Merge Sort ****************************************************** //
    private IntDLLNode merge(IntDLLNode first, IntDLLNode last) {
        if (first == null) {
            return last;
        }
        
        if (last == null) {
            return first;
        }
        
        this.compCount++;
        
        if (first.getData() < last.getData()) {
            first.setNext(merge(first.getNext(), last));
            first.getNext().setPrev(first);
            first.setPrev(null);
            return first;
        } else {
            last.setNext(merge(first, last.getNext()));
            last.getNext().setPrev(last);
            last.setPrev(null);
            return last;
        }
    }
    
    private IntDLLNode getMiddle(IntDLLNode first) {
        IntDLLNode fastNode = first;
        IntDLLNode slowNode = first;
        
        while (fastNode.getNext() != null && fastNode.getNext().getNext() != null) {
            slowNode = slowNode.getNext();
            fastNode = fastNode.getNext().getNext();
        }
        
        IntDLLNode hold = slowNode.getNext();
        slowNode.setNext(null);
        return hold;
    }
    
    private IntDLLNode recMergeSort(IntDLLNode first) {
        if (first == null || first.getNext() == null) {
            return first;
        }
        
        IntDLLNode middle = getMiddle(first);
        first = recMergeSort(first);
        middle = recMergeSort(middle);
        return merge(first, middle);
    }
    
    public void mergeSort() {
        IntDLLNode node;
        node = recMergeSort(this.list.getFirstNode());
        this.list.setNewFirstNode(node);
    }

    // Quick Sort ****************************************************** //    
    private void swap(IntDLLNode node1, IntDLLNode node2) {
        int hold = node1.getData();
        node1.setData(node2.getData());
        node2.setData(hold);
    }
    
    private IntDLLNode split(IntDLLNode first, IntDLLNode last) {
        int splitVal = first.getData();
        IntDLLNode saveF = last.getNext(), item = last;
        
        while (item != first) {
            this.compCount++;
            
            if (item.getData() > splitVal) {
                saveF = (saveF == null) ? last : saveF.getPrev();
                swap(saveF, item);
            }
            
            item = item.getPrev();
        }
        
        saveF = (saveF == null) ? last : saveF.getPrev();
        swap(saveF, first);
        return saveF;
    }
    
    private void recQuickSort(IntDLLNode first, IntDLLNode last) {
        if (last != null && first != last.getNext() && first != last) {
            IntDLLNode splitPoint = split(first, last);
            recQuickSort(first, splitPoint.getPrev());
            recQuickSort(splitPoint.getNext(), last);
        }
    }
    
    public void quickSort() {
        recQuickSort(this.list.getFirstNode(), this.list.getLastNode());
    }
    
    // Binary Search *************************************************** //    
    private IntDLLNode getBinaryMiddle(IntDLLNode first, IntDLLNode last) {
        if (first == null) {
            return null;
        }
        
        IntDLLNode slow = first;
        IntDLLNode fast = first;
        
        while (fast != last && fast != null) {
            fast = fast.getNext();
            
            if (fast != last) {
                slow = slow.getNext();
                
                if (fast != null) {
                    fast = fast.getNext();
                }
            }
        }
        
        return slow;
    }
    
    public boolean binarySearch(int element) {
        IntDLLNode first = this.list.getFirstNode();
        IntDLLNode last = this.list.getLastNode();
        
        while (first != last) {
            IntDLLNode middle = getBinaryMiddle(first, last);
            this.compCount++;
            
            if (middle.getData() < element) {
                if (middle == this.list.getFirstNode() || middle == this.list.getLastNode()) {
                    return false;
                }
                
                first = middle.getNext();
            } else if (middle.getData() > element) {
                if (middle == this.list.getFirstNode() || middle == this.list.getLastNode()) {
                    return false;
                }
                
                last = middle.getPrev();
            } else {
                return true;
            }
            
            this.compCount++;
            
            if (first.getData() == element || last.getData() == element) {
                return true;
            }
        }
        
        return false;
    }
    
    // Linear Search *************************************************** //
    public boolean linearSearch(int element) {
        IntDLLNode first = this.list.getFirstNode();
        
        while (first != null) {
            this.compCount++;
            
            if (element == first.getData()) {
                return true;
            }
            
            first = first.getNext();
        }
        
        return false;
    }
}
