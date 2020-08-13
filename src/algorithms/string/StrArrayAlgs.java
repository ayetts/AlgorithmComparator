package algorithms.string;
import java.io.*;

public class StrArrayAlgs {
    private final int size;
    private final String[] values;
    private int compCount = 0;
    
    public StrArrayAlgs(int size, String order) {
        this.size = size;
        this.values = new String[size];
        initValues(order);
    }

    private void initValues(String order) {
        String fileName = "";
        
        switch (order) {
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
                this.values[i] = in.readLine();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public int getCompCount() {
        return this.compCount;
    }
    
    public boolean isSorted() {
        boolean sorted = true;
        
        for (int index = 0; index < (size - 1); index++) {
            if (this.values[index].compareTo(this.values[index + 1]) > 0) {
                sorted = false;
            }
        }
        
        return sorted;
    }

    public void swap(int index1, int index2) {
        String temp = this.values[index1];
        this.values[index1] = this.values[index2];
        this.values[index2] = temp;
    }

    public String getValues() {
        String str = "";
        
        for (int i = 0; i < this.size / 2; i++) {
            for (int j = 0; j < 2; j++) {
              str += this.values[(i * 2) + j] + " ";
            }
            
            str += "\n";
        }
        
        return str;
    }

    // Bubble Sort ***************************************************** //
    private void bubbleUp(int startIndex, int endIndex) {
        for (int index = endIndex; index > startIndex; index--) {
            this.compCount++;
            
            if (this.values[index].compareTo(this.values[index - 1]) < 0) {
                swap(index, index - 1);
            }
        }
    }
 
    public void bubbleSort() {
        int current = 0;
        
        while (current < (this.size - 1)) {
            bubbleUp(current, this.size - 1);
            current++;
        }
    }

    // Insertion Sort ************************************************** //
    private void insertItem(int startIndex, int endIndex) {
        int current = endIndex;
        boolean finished = false;
        boolean moreToSearch = true;
        
        while (moreToSearch && !finished) {
            this.compCount++;
            
            if (this.values[current].compareTo(this.values[current - 1]) < 0) {
                swap(current, current - 1);
                current--;
                moreToSearch = (current != startIndex);
            } else {
                finished = true;
            }
        }
    }
 
    public void insertionSort() {
        for (int count = 1; count < this.size; count++) {
            insertItem(0, count);
        }
    }

    // Merge Sort ****************************************************** //
    private void merge (int leftFirst, int leftLast, int rightFirst, int rightLast) {
        String[] tempArray = new String[this.size];
        int index = leftFirst;
        int saveFirst = leftFirst;
 
        while ((leftFirst <= leftLast) && (rightFirst <= rightLast)) {
            this.compCount++;
            
            if (this.values[leftFirst].compareTo(this.values[rightFirst]) < 0) {
                tempArray[index] = this.values[leftFirst];
                leftFirst++;
            } else {
                tempArray[index] = this.values[rightFirst];
                rightFirst++;
            }
            
            index++;
        }
 
        while (leftFirst <= leftLast) {
            tempArray[index] = this.values[leftFirst];
            leftFirst++;
            index++;
        }
 
        while (rightFirst <= rightLast) {
            tempArray[index] = this.values[rightFirst];
            rightFirst++;
            index++;
        }
 
        for (index = saveFirst; index <= rightLast; index++) {
            this.values[index] = tempArray[index];
        }
    }

    private void recMergeSort(int first, int last) {
        if (first < last) {
            int middle = (first + last) / 2;
            recMergeSort(first, middle);
            recMergeSort(middle + 1, last);
            merge(first, middle, middle + 1, last);
        }
    }
    
    public void mergeSort() {
        recMergeSort(0, this.size - 1);
    }

    // Quick Sort ****************************************************** //
    private int split(int first, int last) {
        boolean onCorrectSide;
        String splitVal = this.values[first];
        int saveF = first;
        first++;
        
        do {
            onCorrectSide = true;
            
            while (onCorrectSide) {
                this.compCount++;
                
                if (this.values[first].compareTo(splitVal) > 0) {
                    onCorrectSide = false;
                } else {
                    first++;
                    onCorrectSide = (first <= last);
                }
            }
 
            onCorrectSide = (first <= last);
            
            while (onCorrectSide) {
                this.compCount++;
                
                if (this.values[last].compareTo(splitVal) <= 0) {
                    onCorrectSide = false;
                } else {
                    last--;
                    onCorrectSide = (first <= last);
                }
            }
   
            if (first < last) {
                swap(first, last);
                first++;
                last--;
            }
        } while (first <= last);

        swap(saveF, last);
        return last;
    }

    private void recQuickSort(int first, int last) {
        if (first < last) {
            int splitPoint;
            splitPoint = split(first, last);
            recQuickSort(first, splitPoint - 1);
            recQuickSort(splitPoint + 1, last);
        }
    }
    
    public void quickSort() {
        recQuickSort(0, this.size - 1);
    }

    // Heap Sort *******************************************************//
    private int newHole(int hole, int lastIndex, String item) {
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;
        
        if (left > lastIndex) {
            return hole;
        }         
        
        if (left == lastIndex) {
            this.compCount++;
            return (item.compareTo(this.values[left]) >= 0) ? hole : left;
        } else {                
            if (this.values[left].compareTo(this.values[right]) < 0) {
                this.compCount++;
                return (this.values[right].compareTo(item) <= 0) ? hole : right;
            } else {
                this.compCount++;
                return (this.values[left].compareTo(item) <= 0) ? hole : left;
            }
        }
    }

    private void reheapDown(String item, int root, int lastIndex) {
        int hole = root;
        int newhole = newHole(hole, lastIndex, item);
        
        while (newhole != hole) {
            this.values[hole] = this.values[newhole];
            hole = newhole;
            newhole = newHole(hole, lastIndex, item);
        }
        this.values[hole] = item;
    }

    public void heapSort() {
        int index;
        for (index = this.size / 2 - 1; index >= 0; index--) {
            reheapDown(this.values[index], index, this.size - 1);
        }
        
        for (index = this.size - 1; index >=1; index--) {
            swap(0, index);
            reheapDown(this.values[0], 0, index - 1);
        }
    }
    
    // Linear Search ***************************************************//
    public boolean linearSearch(String element) {
        for (int i = 0; i < this.size; i++) {
            this.compCount++;
            if (this.values[i].equals(element)) {
                return true;
            }
        }
        
        return false;
    }
    
    // Binary Search ***************************************************//
    public boolean binarySearch(String element) { 
        int low = 0;
        int high = this.size - 1;

        while (high >= low) {
                this.compCount++;
                int mid = (low + high) / 2;
                
                if (this.values[mid].equals(element)) {
                    return true;
                } else if (this.values[mid].compareTo(element) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
        }
        
        return false;
    }
}
