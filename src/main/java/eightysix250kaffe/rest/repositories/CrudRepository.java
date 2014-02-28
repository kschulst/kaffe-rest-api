package eightysix250kaffe.rest.repositories;

import java.util.Set;

public interface CrudRepository<T, ID> {
    T findById(ID id);
    Set<T> list();
    T save(T entity);
    boolean delete(T entity);
}
