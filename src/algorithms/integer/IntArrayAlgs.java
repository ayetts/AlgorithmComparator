package algorithms.integer;
import java.io.*;

public class IntArrayAlgs {
    private final int size;
    private final int[] values;
    private int compCount = 0;
    
    public IntArrayAlgs(int size, String order) {
        this.size = size;
        this.values = new int[size];
        initValues(order);
    }

    private void initValues(String order) {
        String fileName = "";
        
        switch (order) {
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
                this.values[i] = Integer.parseInt(in.readLine());
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
        
        for (int index = 0; index < (this.size - 1); index++) {
            if (this.values[index] > this.values[index + 1]) {
                sorted = false;
            }
        }
        
        return sorted;
    }

    public void swap(int index1, int index2) {
        int temp = this.values[index1];
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
            
            if (this.values[index] < this.values[index - 1]) {
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
            
            if (this.values[current] < this.values[current - 1]) {
                swap(current, current - 1);
                current--;
                moreToSearch = (current != startIndex);
            } else {
                finished = true;
            }
        }
    }
 
    public void insertionSort() {
        for (int count = 1; count < size; count++) {
            insertItem(0, count);
        }
    }

    // Merge Sort ****************************************************** //
    private void merge (int leftFirst, int leftLast, int rightFirst, int rightLast) {
        int[] tempArray = new int [size];
        int index = leftFirst;
        int saveFirst = leftFirst;
 
        while ((leftFirst <= leftLast) && (rightFirst <= rightLast)) {
            compCount++;
            
            if (values[leftFirst] < values[rightFirst]) {
                tempArray[index] = values[leftFirst];
                leftFirst++;
            } else {
                tempArray[index] = values[rightFirst];
                rightFirst++;
            }
            
            index++;
        }
 
        while (leftFirst <= leftLast) {
            tempArray[index] = values[leftFirst];
            leftFirst++;
            index++;
        }
 
        while (rightFirst <= rightLast) {
            tempArray[index] = values[rightFirst];
            rightFirst++;
            index++;
        }
 
        for (index = saveFirst; index <= rightLast; index++) {
            values[index] = tempArray[index];
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
        recMergeSort(0, size - 1);
    }

    // Quick Sort ****************************************************** //
    private int split(int first, int last) {
        boolean onCorrectSide;
        int splitVal = values[first];
        int saveF = first;
        first++;
 
        do {
            onCorrectSide = true;
            
            while (onCorrectSide) {
                compCount++;
                
                if (values[first] > splitVal) {
                    onCorrectSide = false;
                } else {
                    first++;
                    onCorrectSide = (first <= last);
                }
            }
 
            onCorrectSide = (first <= last);
            
            while (onCorrectSide) {
                compCount++;
                
                if (values[last] <= splitVal) {
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
        recQuickSort(0, size - 1);
    }

    // Heap Sort ******************************************************* //
    private int newHole(int hole, int lastIndex, int item) {
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;
        
        if (left > lastIndex) {
            return hole;         
        } else {
            if (left == lastIndex) {
                compCount++;
                return (item >= values[left]) ? hole : left;
            } else {                
                if (values[left] < values[right]) {
                    compCount++;
                    return (values[right] <= item) ? hole : right;
                } else {
                    compCount++;
                    return (values[left] <= item) ? hole : left;
                }
            }
        }
    }

    private void reheapDown(int item, int root, int lastIndex) {
        int hole = root;
        int newhole = newHole(hole, lastIndex, item);
        
        while (newhole != hole) {
            values[hole] = values[newhole];
            hole = newhole;
            newhole = newHole(hole, lastIndex, item);
        }
        
        values[hole] = item;
    }

    public void heapSort() {
        int index;
        
        for (index = size/2 - 1; index >= 0; index--) {
            reheapDown(values[index], index, size - 1);
        }
        
        for (index = size - 1; index >=1; index--) {
            swap(0, index);
            reheapDown(values[0], 0, index - 1);
        }
    }
    
    // Linear Search *************************************************** //    
    public boolean linearSearch(int element) {
        for (int i = 0; i < size; i++) {
            compCount++;
            
            if (values[i] == element) {
                return true;
            }
        }
        
        return false;
    }
    
    // Binary Search *************************************************** //    
    public boolean binarySearch(int element) { 
        int low = 0;
        int high = size - 1;
        
        while (high >= low) {
            compCount++;
            int mid = (low + high) / 2;
            
            if (values[mid] == element) {
                return true;
            } else if (values[mid] < element) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return false;
    }
}
