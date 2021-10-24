package noob.practising.dao;

import java.util.List;

/**
 * <p>Persisting layer interface</p>
 * <dl>
 *     <dt>find(id)</dt>
 *     <dd>To get a T type from its id</dd>
 *     <dt>find()</dt>
 *     <dd>To get all  T types </dd>
 *     <dt>add(T t)</dt>
 *     <dd>Insert a  T type</dd>
 *     <dt>update(T t)</dt>
 *     <dd>To update a  T type </dd>
 *     <dt>delete(T t)</dt>
 *     <dd>To delete a  T type</dd>
 * </dl>
 *
 * @param <T> Parametrized type for enable multiple implementations
 */
public interface Dao<T> {

    /**
     * Find an entity from a table in a database from its id
     *
     * @param id
     * @return T {@link T t}
     */
    T find(int id);

    /**
     * Find all entities from a table in a database
     *
     * @return List<T> {@link  List<T>  List<T>.class}
     */
    List<T> find();

    /**
     * Adds an entity T
     *
     * @param t
     */
    void add(T t);

    /**
     * Updates an entity T
     *
     * @param t
     */
    void update(T t);

    /**
     * Removes an entity T
     *
     * @param t
     */
    void delete(T t);
}
