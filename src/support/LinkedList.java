package support;
 
public class LinkedList<T extends Comparable> {
    protected DLLNode listFirst;
    protected DLLNode listLast;
    protected int size;
 
    public LinkedList() {
        this.listFirst = null;
        this.listLast = null;
        this.size = 0;
    }
  
    public DLLNode getFirstNode() {
        return this.listFirst;
    }
  
    public DLLNode getLastNode() {
        return this.listLast;
    }
    
    public int getSize() {
        return this.size;
    }
  
    public void setNewFirstNode(DLLNode first) {
        DLLNode last = first;
        
        while (last.getNext() != null) {
            last = last.getNext();
        }
        
        this.listFirst = first;
        this.listLast = last;
    }

    public void addFront (Comparable element) {
        DLLNode newNode = new DLLNode(element);
        newNode.setLink(this.listFirst);
        newNode.setPrev(null);
        
        if (this.listFirst == null) {
            this.listFirst = newNode;
            this.listLast = newNode;
        } else {
            this.listFirst.setPrev(newNode);
            this.listFirst = newNode;
        }
        
        size++;
    }

    public void addEnd (Comparable element) {
        DLLNode newNode = new DLLNode(element);
        newNode.setLink(null);
        newNode.setPrev(this.listLast);
        
        if (this.listFirst == null) {
            this.listFirst = newNode;
            this.listLast = newNode;
        } else {
            this.listLast.setLink(newNode);
            this.listLast = newNode;
        }
        
        this.size++;
    }
}
