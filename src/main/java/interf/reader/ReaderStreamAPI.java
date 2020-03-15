package interf.reader;

import interf.Main;
import reader.IReader;
import ru.vsu.lab.repository.IRepository;

import java.util.logging.Logger;

public class ReaderStreamAPI implements IReader {
    IRepository repository;
    String path;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public ReaderStreamAPI(IRepository repository, String path) {
        this.repository = repository;
        this.path = path;
    }

    @Override
    public IRepository read() {


        return this.repository;
    }
}
