package interf.repository;

import interf.Main;
import interf.sorts.Sort;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonRepository implements IPersonRepository {
    private IPerson[] arr;
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public PersonRepository() {
        this.arr = new IPerson[0];
    }

    @Override
    public void add(IPerson person) {
        logger.log(Level.INFO, "add person in "+this.getClass().getName());

        IPerson[] newArr = new IPerson[this.arr.length + 1];

        for (int i = 0; i < this.arr.length; i++) {
            if (arr[i] != null) newArr[i] = this.arr[i];
        }
        newArr[this.arr.length] = person;
        this.arr = newArr;
    }

    @Override
    public IPerson get(int index) {
        logger.log(Level.INFO, "get person in "+this.getClass().getName());
        try {
            return this.arr[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe(e.getMessage());

            System.out.println(String.format("Индекс " + index + " выходит за границы массива."));
            return null;
        }
    }

    @Override
    public IPerson delete(int index) {
        logger.log(Level.INFO, "delete person in "+this.getClass().getName());
        try {
            IPerson[] newArr = new IPerson[this.arr.length - 1];

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
    public IPerson set(int index, IPerson person) {
        logger.log(Level.INFO, "set person in "+this.getClass().getName());
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
    public void add(int index, IPerson person) {
        logger.log(Level.INFO, "set person in "+this.getClass().getName());
        try {
            IPerson[] newArr = new IPerson[this.arr.length + 1];

            newArr[index] = person;

            System.arraycopy(this.arr, 0, newArr, 0, index );
            System.arraycopy(this.arr, index, newArr, index + 1, this.arr.length - index);

            this.arr = newArr;
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe(e.getMessage());

            e.printStackTrace();
            System.out.println(String.format("Индекс " + index + " выходит за границы массива."));
        }
    }

    @Override
    public List<IPerson> toList() {
        logger.log(Level.INFO, "toList person in "+this.getClass().getName());
        return Arrays.asList(this.arr);
    }

    @Override
    public void sortBy(Comparator<IPerson> comparator) {
        logger.log(Level.INFO, "sortBy person in "+this.getClass().getName());
        Sort st = new Sort(this.arr);
        this.arr = (IPerson[]) st.bubbleSort(comparator);
        this.arr = (IPerson[]) st.selectionSort(comparator);
        this.arr = (IPerson[]) st.insertionSort(comparator);
    }

    @Override
    public IRepository<IPerson> searchBy(Predicate<IPerson> condition) {
        logger.log(Level.INFO, "searchBy person in "+this.getClass().getName());
        Repository rep = new Repository();
        for (IPerson pr : this.arr) {
            if (condition.test(pr)) rep.add(pr);
        }
        return rep;
    }
}
