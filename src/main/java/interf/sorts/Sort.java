package interf.sorts;

import interf.Main;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sort {
    private Object[] arr;
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public Sort(Object[] arr) {
        this.arr = arr;
    }

    public Object[] bubbleSort(Comparator comparator) {
        logger.log(Level.INFO, "bubbleSort in "+this.getClass().getName());
        Object[] arr = this.arr;
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) < 0) {
                    Object temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        return arr;
    }

    public Object[] selectionSort(Comparator comparator) {
        logger.log(Level.INFO, "selectionSort in "+this.getClass().getName());
        Object[] arr = this.arr;
        for (int i = 0; i < arr.length; i++) {
            Object min = arr[i];
            int min_i = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], min) < 0) {
                    min = arr[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                Object tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
        return arr;
    }

    public Object[] insertionSort(Comparator comparator) {
        logger.log(Level.INFO, "insertionSort in "+this.getClass().getName());
        Object[] arr = this.arr;
        Object key;
        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            int j = i - 1;

            while (j >= 0 && comparator.compare(arr[j], arr[j + 1]) < 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
