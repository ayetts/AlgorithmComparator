package support;
 
public class IntLinkedList {
    protected IntDLLNode listFirst;
    protected IntDLLNode listLast;
    protected int size;
 
    public IntLinkedList() {
        this.listFirst = null;
        this.listLast = null;
        this.size = 0;
    }
  
    public IntDLLNode getFirstNode() {
        return this.listFirst;
    }
  
    public IntDLLNode getLastNode() {
        return this.listLast;
    }
    
    public int getSize() {
        return this.size;
    }
  
    public void setNewFirstNode(IntDLLNode first) {
        IntDLLNode last = first;
        
        while (last.getNext() != null) {
            last = last.getNext();
        }
        
        this.listFirst = first;
        this.listLast = last;
    }

    public void addFront (int element) {
        IntDLLNode newNode = new IntDLLNode(element);
        newNode.setNext(this.listFirst);
        newNode.setPrev(null);
        
        if (this.listFirst == null) {
            this.listFirst = newNode;
            this.listLast = newNode;
        } else {
            this.listFirst.setPrev(newNode);
            this.listFirst = newNode;
        }
        
        this.size++;
    }

    public void addEnd (int element) {
        IntDLLNode newNode = new IntDLLNode(element);
        newNode.setNext(null);
        newNode.setPrev(this.listLast);
        
        if (this.listFirst == null) {
            this.listFirst = newNode;
            this.listLast = newNode;
        } else {
            this.listLast.setNext(newNode);
            this.listLast = newNode;
        }
        
        this.size++;
    }
}
