package support;

public class IntDLLNode {
    private IntDLLNode next;
    private IntDLLNode prev;
    private int data;
  
    public IntDLLNode(int data) {
        this.next = null;
        this.prev = null;
        this.data = data;
    }
        
    public IntDLLNode getNext() {
        return this.next;
    }
    
    public void setNext(IntDLLNode next) {
        this.next = next;
    }
    
    public IntDLLNode getPrev() {
        return this.prev;
    }
        
    public void setPrev(IntDLLNode prev) {
        this.prev = prev;
    }
    
    public int getData() {
        return data;
    }
    
    public void setData(int data) {
        this.data = data;
    }
}
