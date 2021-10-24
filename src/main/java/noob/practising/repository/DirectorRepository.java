package noob.practising.repository;

import noob.practising.dao.DirectorDao;
import noob.practising.model.Director;

import java.util.List;

/**
 * Repository pattern class for Directors
 * <p>
 * This class belongs to the business layer and abstracts the dao
 */
public class DirectorRepository implements Repository<Director> {

    /**
     * Default file name for loading database properties
     */
    private final String properties = "db.properties";

    /**
     * Dao object to perform database queries
     */
    private DirectorDao dao;

    /**
     * Empty constructor to use default properties file
     */
    public DirectorRepository() {
        dao = new DirectorDao();
        dao.setProperties(properties);
    }

    /**
     * Not empty constructor to use a custom properties file
     */
    public DirectorRepository(String properties) {
        dao = new DirectorDao();
        dao.setProperties(properties);
    }

    @Override
    public Director find(int id) {
        return dao.find(id);
    }

    @Override
    public List<Director> find() {
        return dao.find();
    }

    @Override
    public void add(Director director) {
        dao.add(director);
    }

    @Override
    public void update(Director director) {
        dao.update(director);
    }

    @Override
    public void delete(Director director) {
        dao.delete(director);
    }
}
