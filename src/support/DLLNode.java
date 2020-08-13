package support;

public class DLLNode<T extends Comparable> {
    private DLLNode<T> next;
    private DLLNode<T> prev;
    private T data;
    
    public DLLNode(T data) {
        this.next = null;
        this.prev = null;
        this.data = data;
    }
     
    public DLLNode<T> getNext() {
        return this.next;
    }
    
    public void setLink(DLLNode<T> next) {
        this.next = next;
    }
    
    public DLLNode getPrev() {
        return this.prev;
    }
    
    public void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }
    
    public T getData() {
        return this.data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
