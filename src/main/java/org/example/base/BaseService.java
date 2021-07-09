package org.example.base;

import java.util.List;

public interface BaseService<T> {

    public T insertOne(T t);

    public List<T> getAll();

    public T get(long id);

    public T remove(long id);

    public T update(long id, T t);


}
