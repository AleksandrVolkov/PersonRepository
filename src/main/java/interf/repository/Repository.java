package interf.repository;

import interf.Main;
import interf.sorts.Sort;
import ru.vsu.lab.repository.IRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository<T> implements IRepository {
    //    @LabInject
//    ISorter sorter;
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private Object[] arr;
    private String pole;

    public Repository() {
        this.arr = new Object[0];
    }

    @Override
    public void add(Object person) {
        Object[] newArr = new Object[this.arr.length + 1];

        for (int i = 0; i < this.arr.length; i++) {
            if (arr[i] != null) newArr[i] = this.arr[i];
        }
        newArr[this.arr.length] = person;
        this.arr = newArr;
    }

    @Override
    public Object get(int index) {
        try {
            return this.arr[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(String.format("Индекс " + index + " выходит за границы массива."));
            return null;
        }
    }

    @Override
    public Object delete(int index) {

        try {
            Object[] newArr = new Object[this.arr.length - 1];

            for (int i = 0; i < this.arr.length; i++) {
                if (i < index) {
                    newArr[i] = this.arr[i];
                }
                if (i > index) {
                    newArr[i - 1] = this.arr[i];
                }
            }
            this.arr = newArr;
            return this.arr[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe(e.getMessage());

            System.out.println(String.format("Индекс " + index + " выходит за границы массива."));
            return null;
        }
    }

    @Override
    public Object set(int index, Object person) {
        try {
            this.arr[index] = person;
            return person;
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe(e.getMessage());

            System.out.println(String.format("Индекс " + index + " выходит за границы массива."));
            return null;
        }
    }

    @Override
    public void add(int index, Object person) {
        try {
            Object[] newArr = new Object[this.arr.length + 1];

            newArr[index] = person;

            System.arraycopy(this.arr, 0, newArr, 0, index);
            System.arraycopy(this.arr, index, newArr, index + 1, this.arr.length - index);

            this.arr = newArr;
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe(e.getMessage());

            e.printStackTrace();
            System.out.println(String.format("Индекс " + index + " выходит за границы массива."));
        }
    }

    @Override
    public List toList() {
        return Arrays.asList(this.arr);
    }

    @Override
    public void sortBy(Comparator comparator) {
        Sort st = new Sort(this.arr);
        this.arr = st.bubbleSort(comparator);
        this.arr = st.selectionSort(comparator);
        this.arr = st.insertionSort(comparator);
    }

    @Override
    public IRepository searchBy(Predicate condition) {
        Repository rep = new Repository();
        for (Object pr : this.arr) {
            if (condition.test(pr)) rep.add(pr);
        }
        return rep;
    }
}
