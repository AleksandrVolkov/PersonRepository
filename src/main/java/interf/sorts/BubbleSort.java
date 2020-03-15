package interf.sorts;

import interf.Main;
import interf.sorts.sorter.ISorter;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BubbleSort implements ISorter {
    private Object[] arr;
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public BubbleSort(Object[] arr) {
        this.arr = arr;
    }

    @Override
    public Object[] Sort(Comparator comparator) {
        logger.log(Level.INFO, "sort Object in "+this.getClass().getName());
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
}
