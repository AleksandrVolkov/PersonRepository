package interf.entities;

import interf.Main;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Person implements IPerson {
    private Integer id;
    private String firstName;
    private String lastName;  private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Person() {
    }

    private LocalDate birthday;
    private Integer age;
    private Gender gender;
    private IDivision division;
    private BigDecimal salary;

    public Person(Integer id, String firstName, String lastName, LocalDate birthday, Gender gender, IDivision division, BigDecimal salary) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.age = Period.between(this.birthday, LocalDate.now()).getYears();
        this.gender = gender;
        this.division = division;
        this.salary = salary;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public LocalDate getBirthdate() {
        return this.birthday;
    }

    @Override
    public void setBirthdate(LocalDate birthdate) {
        this.birthday = birthday;
    }


    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public IDivision getDivision() {
        return this.division;
    }

    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }

    @Override
    public BigDecimal getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
