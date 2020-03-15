package interf.entities;
import interf.Main;
import ru.vsu.lab.entities.IDivision;;import java.util.logging.Level;
import java.util.logging.Logger;

public class Division implements IDivision {
    private Integer id;
    private String name;  private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Division(Integer id, String name) {
        logger.log(Level.INFO, "created division");
        this.id = id;

        this.name = name;
    }

    public Division() {
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
