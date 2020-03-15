package interf.annotation;

import interf.entities.Person;
import ru.vsu.lab.repository.IRepository;

import java.lang.annotation.*;
import java.util.Comparator;

@Target(value=ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface LabInject {
    String name();
    String type() default  "string";
    String Sort();
//    public static <T> T inject(T obj);
}