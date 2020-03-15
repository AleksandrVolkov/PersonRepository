package interf.stream;

import interf.Main;
import interf.entities.Person;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StreamAPI {
    public static List<Person> listPerson;
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public StreamAPI(List<Person> listPerson) {
        this.listPerson = listPerson;
    }

    public Map getMap1() {  //Map<String, BigDecimal>
        logger.log(Level.INFO, "Get division sum salary");
        Map<String, List<Person>> a1 = listPerson
                .stream()
                .collect(Collectors.groupingBy(p -> p.getDivision().getName()));

        return a1.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        p -> p.getValue()
                                .stream()
                                .map(Person::getSalary).reduce(BigDecimal::add).get()));
    }

    public Map getMap2() {  // Map<Integer, Person>
        logger.log(Level.INFO, "Get person where age>30 and name contain literal - a ");

        return listPerson.stream()
                .filter(p -> p.getAge() > 30 &&
                        p.getFirstName().toLowerCase().contains("a") &&
                        p.getSalary().compareTo(new BigDecimal(2000)) < 0)
                .collect(Collectors.toMap(Person::getId,
                        p -> p));
    }

    public Map getMap3() {  // Map<Integer, Person>
        logger.log(Level.INFO, "Get person where  and name contain literals - aa ");
        return listPerson.stream()
                .filter(p -> p.getFirstName().toLowerCase().contains("aа"))
                .collect(Collectors.toMap(Person::getId,
                        p -> p));
    }

    //возвращает группировку по дивизионам, если бы было не биг десимал, возвращал бы и суммарную зарплату по ним
//        Map<String, Long> ad1 = listPerson
//                .stream()
//                .collect(Collectors.groupingBy(p -> p.getDivision().getName(), Collectors.summingLong(Person1::getSalary)));

//            List<Optional<BigDecimal>> collect = a1.entrySet()
//                .stream()
//                .map(p -> p.getValue()
//                        .stream()
//                        .map(Person::getSalary).reduce(BigDecimal::add))
//                .collect(Collectors.toList());
//
//        List<Optional<BigDecimal>> collect3 = a1.entrySet()
//                .stream()
//                .map(p -> p.getValue()
//                        .stream()
//                        .map(Person::getSalary).reduce(BigDecimal::add))
//                .collect(Collectors.toList());

}
