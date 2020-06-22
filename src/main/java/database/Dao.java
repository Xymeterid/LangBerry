package database;

import java.util.Collection;
import java.util.Optional;

//Стандартний Dao інтерфейс для роботи з базами даних
public interface Dao<T> {

    Optional<T> get(int id);
    Collection<T> getAll();
    int save(T t);
    void update(T t);
    void delete(T t);
    Collection<T> getAlThatMatch(String query);
}