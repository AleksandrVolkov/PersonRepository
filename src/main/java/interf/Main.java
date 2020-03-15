package interf;

import interf.entities.Person;
import interf.factory.LabFactory;
import interf.reader.Reader;
import interf.repository.PersonRepository;
import interf.repository.Repository;
import interf.stream.StreamAPI;
import reader.IReader;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.*;

public class Main {
    //    private static final Logger logger = Logger.getLogger(Main.class.getName());
    static Logger logger = Logger.getLogger(Main.class.getName());

    static FileHandler fh;

    static {
        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("src/main/resources/Logers/log.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }
//    static {
//        try (FileInputStream ins = new FileInputStream("src/main/resources/Logers/log.txt")) {
//            LogManager.getLogManager().readConfiguration(ins);
//        } catch (Exception ignore) {
//            ignore.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        logger.log(Level.INFO, "Logged!");

        IPersonRepository personRepository =new PersonRepository();

        personRepository.get(10);

        ILabFactory labF = new LabFactory();
        logger.log(Level.INFO, "Create Factory");
        IRepository<Person> rp = labF.createRepository(Person.class);
        logger.log(Level.INFO, "Create Repository");

        IReader reader = new Reader(rp, ".\\src\\main\\resources\\persons.csv");

        List ipr = reader.read().toList();
        logger.log(Level.INFO, "File readed");

        StreamAPI streamAPI = new StreamAPI(ipr);

//        logger.log(Level.INFO, "Get division sum salary");
        streamAPI.getMap1().forEach((key, value) -> {
            System.out.println("Division : " + key + " sum_salary : " + value);
        });

        streamAPI.getMap2().forEach((key, value) -> {
            outputPerson((Person) value);
            System.out.println(outputPerson((Person) value));
        });

        streamAPI.getMap3().forEach((key, value) -> {
            outputPerson((Person) value);
            System.out.println(outputPerson((Person) value));
        });


    }

    public static void vv() throws NoSuchFieldException, IllegalAccessException {
        Repository rp = new Repository();
        Field field = Repository.class.getDeclaredField("pole"); //getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(rp, "string");

        int dadsd = 1;

    }

    static void printLPerson(List<Object> dl) {
        logger.log(Level.INFO, "print Person");
        for (Object pr : dl) {
            System.out.println(outputPerson((Person) pr));
        }
    }

    static String outputPerson(Person inpPr) {
        return String.format(" | ID " + inpPr.getId() + " | Имя " + inpPr.getFirstName() + ' ' + inpPr.getLastName() +
                " | ДР " + inpPr.getBirthdate().toString() + " | Возраст " + inpPr.getAge() + " | Пол " + inpPr.getGender() +
                " | classesWithInterface.Division " + inpPr.getDivision().getName() + ' ' + inpPr.getDivision().getId() + " | Salary " + inpPr.getSalary());
    }
}
