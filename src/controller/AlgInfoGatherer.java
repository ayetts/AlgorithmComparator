package controller;

import algorithms.string.StrRefAlgs;
import algorithms.string.StrHashingAlg;
import algorithms.string.StrArrayAlgs;
import algorithms.integer.IntRefAlgs;
import algorithms.integer.IntHashingAlg;
import algorithms.integer.IntArrayAlgs;

public class AlgInfoGatherer {
    public static Object[] getInfo(String impl, String type, int size, String order, String alg, String value) {
        Object[] result = new Object[4];
        
        if (impl.equals("Array") && type.equals("String")) {
            result = getArrayStrInfo(size, order, alg, value);
        } else if (impl.equals("Array") && type.equals("Integer")) {
            result = getArrayIntInfo(size, order, alg, value);
        } else if (impl.equals("Linked List") && type.equals("String")) {
            result = getLLStrInfo(size, order, alg, value);
        } else if (impl.equals("Linked List") && type.equals("Integer")) {
            result = getLLIntInfo(size, order, alg, value);
        } else {
            System.err.println("Error in AlgInfoGatherer.\n" + impl + "\n" + type);    
        }
        
        return result;
    }
    
    private static Object[] getArrayStrInfo(int size, String order, String alg, String value) {
        StrArrayAlgs object = new StrArrayAlgs(size, order);
        String beforeAlg = object.getValues();
        String afterAlg = "";
        Integer compCount = 0; 
        Boolean found = false;
        
        switch (alg) {
            case "Bubble Sort":
                object.bubbleSort();
                afterAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Insertion Sort":
                object.insertionSort();
                afterAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Merge Sort":
                object.mergeSort();
                afterAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Quick Sort":
                object.quickSort();
                afterAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Heap Sort":
                object.heapSort();
                afterAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Linear Search":
                found = object.linearSearch(value);
                afterAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Binary Search":
                found = object.binarySearch(value);
                afterAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Hashing":
                StrHashingAlg hash = new StrHashingAlg(size, order);
                found = hash.search(value);
                beforeAlg = afterAlg = hash.toString();
                compCount = hash.getCompCount();
                break;
                
            default: 
                break;
        }
        
        Object[] result = new Object[4];
        result[0] = beforeAlg; result[1] = afterAlg;
        result[2] = compCount; result[3] = found;
        return result;
    }
    
    private static Object[] getArrayIntInfo(int size, String order, String alg, String f) {
        int find = Integer.parseInt(f);
        IntArrayAlgs object = new IntArrayAlgs(size, order);
        String befAlg = object.getValues(), aftAlg = "";
        Integer compCount = 0; Boolean found = false;
        
        switch (alg) {
            case "Bubble Sort":
                object.bubbleSort();
                aftAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Insertion Sort":
                object.insertionSort();
                aftAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Merge Sort":
                object.mergeSort();
                aftAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Quick Sort":
                object.quickSort();
                aftAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Heap Sort":
                object.heapSort();
                aftAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Linear Search":
                found = object.linearSearch(find);
                aftAlg = object.getValues();
                compCount = object.getCompCount();
                break;
            case "Binary Search":
                found = object.binarySearch(find);
                aftAlg = object.getValues();
                compCount = object.getCompCount();
                break;
                
            case "Hashing":
                IntHashingAlg hash = new IntHashingAlg(size, order);
                found = hash.search(find);
                befAlg = aftAlg = hash.toString();
                compCount = hash.getCompCount();
                break;
                
            default: 
                break;
        }
        
        Object[] result = new Object[4];
        result[0] = befAlg; result[1] = aftAlg;
        result[2] = compCount; result[3] = found;
        return result;
    }
    
    private static Object[] getLLStrInfo(int size, String order, String alg, String f) {
        StrRefAlgs object = new StrRefAlgs(size, order);
        String befAlg = object.toString(), aftAlg = "";
        Integer compCount = 0; Boolean found = false;
        
        switch (alg) {
            case "Bubble Sort":
                object.bubbleSort();
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Insertion Sort":
                object.insertionSort();
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Merge Sort":
                object.mergeSort();
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Quick Sort":
                object.quickSort();
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Linear Search":
                found = object.linearSearch(f);
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Binary Search":
                found = object.binarySearch(f);
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            default: 
                break;
        }
        
        Object[] result = new Object[4];
        result[0] = befAlg; result[1] = aftAlg;
        result[2] = compCount; result[3] = found;
        return result;
    }

    private static Object[] getLLIntInfo(int size, String order, String alg, String f) {
        int find = Integer.parseInt(f);
        IntRefAlgs object = new IntRefAlgs(size, order);
        String befAlg = object.toString(), aftAlg = "";
        Integer compCount = 0; Boolean found = false;
        
        switch (alg) {
            case "Bubble Sort":
                object.bubbleSort();
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Insertion Sort":
                object.insertionSort();
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Merge Sort":
                object.mergeSort();
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Quick Sort":
                object.quickSort();
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Linear Search":
                found = object.linearSearch(find);
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            case "Binary Search":
                found = object.binarySearch(find);
                aftAlg = object.toString();
                compCount = object.getCompCount();
                break;
                
            default: 
                break;
        }
        
        Object[] result = new Object[4];
        result[0] = befAlg; result[1] = aftAlg;
        result[2] = compCount; result[3] = found;
        return result;
    }
}
