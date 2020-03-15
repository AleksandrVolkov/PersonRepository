package interf.reader;

import interf.Main;
import interf.entities.Division;
import interf.entities.Person;
import reader.IReader;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader implements IReader {
    IRepository repository;
    String path;  private static final Logger logger = Logger.getLogger(Main.class.getName());

//    public static void main(String[] args) {
//        ILabFactory lb= new LabFactory();
//        Reader r = new Reader(lb.createRepository(Reader.class),".\\src\\main\\resources\\persons.csv");
//        printLPerson(r.read().toList());
//    }
//    static void printLPerson(List<Object> dl) {
//        for (Object pr : dl) {
//            System.out.println(outputPerson((Person)pr));
//        }
//    }
//    static String outputPerson(Person inpPr) {
//        return String.format(" | ID " + inpPr.getId() + " | Имя " + inpPr.getFirstName() + ' ' + inpPr.getLastName() +
//                " | ДР " + inpPr.getBirthdate().toString() + " | Возраст " + inpPr.getAge() + " | Пол " + inpPr.getGender() +
//                " | classesWithInterface.Division " + inpPr.getDivision().getName() + ' ' + inpPr.getDivision().getId() + " | Salary " + inpPr.getSalary());
//    }


    public Reader(IRepository repository, String path) {
        logger.log(Level.INFO, "created reader");

        this.repository = repository;
        this.path = path;
    }

    @Override
    public IRepository read() {
        logger.log(Level.INFO, " read file");

        Map<String ,IDivision> mapDivision = new HashMap<>();
        String line = "";
        int i =0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) { // ".\\src\\main\\resources\\persons.csv"
            br.readLine();
            while ((line = br.readLine()) != null) {
                if(10000==i++)break;

                String[] el = line.split(";");
                Integer id = new Integer(el[0]);
                String firstName = el[1];
                String lastName = "";
                LocalDate birthday = LocalDate.parse(el[3], DateTimeFormatter.ofPattern("d.MM.yyyy"));
                Integer age = Period.between(birthday, LocalDate.now()).getYears();
                Gender gender = "Male".equals(el[2]) ? Gender.MALE : Gender.FEMALE;
                IDivision division;
                if( !mapDivision.containsKey(el[4]) || mapDivision.isEmpty()) {
                    mapDivision.put(el[4], new Division(id, el[4]));
                    division = mapDivision.get(el[4]);
                }
                else
                    division = mapDivision.get(el[4]);
                BigDecimal salary = new BigDecimal(el[5]);
//                Long salary = new Long(el[5]);

                repository.add(new Person(id, firstName, lastName, birthday, gender, division, salary));
//                repository.add(new Person1(id, firstName, lastName, birthday, gender, division, salary));
            }

        } catch (IOException e) {
            logger.severe(e.getMessage());

            e.printStackTrace();
        }
        return this.repository;
    }
}
