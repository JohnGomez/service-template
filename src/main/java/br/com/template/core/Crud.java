package br.com.template.core;

import java.util.List;

public interface Crud<T,ID> {
    List<T> findAll();
    void create(T model);
    T get(ID id) throws Throwable;
    void delete(ID model);
    void update(ID id, T model);
}
