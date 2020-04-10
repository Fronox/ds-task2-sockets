import java.io.Serializable;

public class InsertionSort implements Serializable {
    int[] array;
    InsertionSort(int[] array){
        this.array = array;
    }

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

    int[] getArray() {
        return this.array;
    }
}
