package algorithms.string;
import java.io.*;
import support.*;

public class StrRefAlgs {
    private final LinkedList<String> list;
    private final int size;
    private int compCount = 0;
    
    public StrRefAlgs(int size, String order) {
        this.list = new LinkedList<>();
        
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
                fileName = "textfiles/RevS" + this.size + ".txt"; //adds in reverse so "Rev"
                break;
                
            case "Reverse":
                fileName = "textfiles/InS" + this.size + ".txt"; //adds in reverse so "In"
                break;
                
            case "Random":
                fileName = "textfiles/RandS" + this.size + ".txt";
                break;
                
            default:
                break;
        }
            
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < this.size; i++) {
                this.list.addFront(in.readLine());
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    @Override
    public String toString() {
        DLLNode hold = this.list.getFirstNode();
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
    private void bubbleUp(DLLNode start, int end) {
        for (int i = 1; i < end; i++) {
            this.compCount++;
            
            if (start.getData().compareTo(start.getNext().getData()) > 0) {
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
    private void insertItem(DLLNode start, DLLNode current) {
        while (current != start) {
            DLLNode item = current.getPrev();
            this.compCount++;
            
            if (current.getData().compareTo(item.getData()) < 0) {
                swap(current, item);
            }
            
            current = current.getPrev();
        }
    }
 
    public void insertionSort() {
        DLLNode current = this.list.getFirstNode();
        
        while (current != null) {
            insertItem(this.list.getFirstNode(), current);
            current = current.getNext();
        }
    }
    
    // Merge Sort ****************************************************** //
    private DLLNode merge(DLLNode first, DLLNode last) {
        if (first == null) {
            return last;
        } 
        
        if (last == null) {
            return first;
        }
        
        this.compCount++;
        
        if (first.getData().compareTo(last.getData()) < 0) {
            first.setLink(merge(first.getNext(), last));
            first.getNext().setPrev(first);
            first.setPrev(null);
            return first;
        } else {
            last.setLink(merge(first, last.getNext()));
            last.getNext().setPrev(last);
            last.setPrev(null);
            return last;
        }
    }
    
    private DLLNode getMiddle(DLLNode first) {
        DLLNode fastNode = first;
        DLLNode slowNode = first;
        
        while (fastNode.getNext() != null && fastNode.getNext().getNext() != null) {
            slowNode = slowNode.getNext();
            fastNode = fastNode.getNext().getNext();
        }
        
        DLLNode hold = slowNode.getNext();
        slowNode.setLink(null);
        return hold;
    }
    
    private DLLNode recMergeSort(DLLNode first) {
        if (first == null || first.getNext() == null) {
            return first;
        }
        
        DLLNode middle = getMiddle(first);
        first = recMergeSort(first);
        middle = recMergeSort(middle);
        return merge(first, middle);
    }
    
    public void mergeSort() {
        DLLNode node;
        node = recMergeSort(this.list.getFirstNode());
        this.list.setNewFirstNode(node);
    }

    // Quick Sort ****************************************************** //    
    private void swap(DLLNode node1, DLLNode node2) {
        String hold = (String) node1.getData();
        node1.setData(node2.getData());
        node2.setData(hold);
    }
    
    private DLLNode split(DLLNode first, DLLNode last) {
        String splitVal = (String) first.getData();
        DLLNode saveF = last.getNext(), item = last;
        
        while (item != first) {
            this.compCount++;
            
            if (item.getData().compareTo(splitVal) > 0) {
                saveF = (saveF == null) ? last : saveF.getPrev();
                swap(saveF, item);
            }
            
            item = item.getPrev();
        }
        
        saveF = (saveF == null) ? last : saveF.getPrev();
        swap(saveF, first);
        return saveF;
    }
    
    private void recQuickSort(DLLNode first, DLLNode last) {
        if (last != null && first != last.getNext() && first != last) {
            DLLNode splitPoint = split(first, last);
            recQuickSort(first, splitPoint.getPrev());
            recQuickSort(splitPoint.getNext(), last);
        }
    }
    
    public void quickSort() {
        recQuickSort(this.list.getFirstNode(), this.list.getLastNode());
    }
    
    // Binary Search *************************************************** //    
    private DLLNode getBinaryMiddle(DLLNode first, DLLNode last) {
        if (first == null) {
            return null;
        }
        
        DLLNode slow = first;
        DLLNode fast = first;
        
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
    
    public boolean binarySearch(String element) {
        DLLNode first = this.list.getFirstNode();
        DLLNode last = this.list.getLastNode();
        
        while (first != last) {
            DLLNode middle = getBinaryMiddle(first, last);
            this.compCount++;
            
            if (middle.getData().compareTo(element) < 0) {
                if (middle == this.list.getFirstNode() || middle == this.list.getLastNode()) {
                    return false;
                }
                
                first = middle.getNext();
            } else if (middle.getData().compareTo(element) > 0) {
                if (middle == this.list.getFirstNode() || middle == this.list.getLastNode()) {
                    return false;
                }
                
                last = middle.getPrev();
            } else {
                return true;
            }
            
            this.compCount++;
            
            if (first.getData().compareTo(element) == 0) {
                return true;
            }
        }
        
        return false;
    }
    
    // Linear Search *************************************************** //
    public boolean linearSearch(Comparable element) {
        DLLNode first = this.list.getFirstNode();
        
        while (first != null) {
            this.compCount++;
            
            if (element.compareTo(first.getData()) == 0) {
                return true;
            }
            
            first = first.getNext();
        }
        
        return false;
    }
}
