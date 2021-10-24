package noob.practising.repository;

import java.util.List;


/**
 * Repository generic interface for a better abstraction from DAO's classes
 *
 * @param <T>
 */
public interface Repository<T> {
    T find(int id);


    List<T> find();


    void add(T t);


    void update(T t);


    void delete(T t);
}
