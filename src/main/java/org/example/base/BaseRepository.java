package org.example.base;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseRepository<T> {

    public T updateOne(T t);

    public T findById(long id);

    public List<T> findAll();

    public T deleteOne(T t);

    public T save(T t);

}
