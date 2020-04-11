import java.io.Serializable;

// insertion sort class
public class InsertionSort implements Serializable {
    // Sorted array variable
    int[] array;

    // Constructor
    InsertionSort(int[] array){
        this.array = array;
    }

    // Sort method
    void sort() {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if(array[i] > array[j]){
                    int buff = array[i];
                    array[i] = array[j];
                    array[j] = buff;
                }
            }
        }
    }

    // Method for current array state obtaining
    int[] getArray() {
        return this.array;
    }
}
