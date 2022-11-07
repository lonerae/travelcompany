package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.model.PersistentClass;
import com.travelcompany.eshop.repository.Repository;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryImpl<T extends PersistentClass> implements Repository<T> {

    private final List<T> list;
    private int index;

    public RepositoryImpl() {
        this.list = new ArrayList<>();
    }
    
    @Override
    public int create(T object) {
        object.setId(index++);
        list.add(object);
        return object.getId();
    }

    @Override
    public T read(int id) {
        for (T object : list) {
            if (object.getId() == id) {
                return object;
            }
        }
        return null;
    }

    @Override
    public List<T> read() {
        return list;
    }

    @Override
    public boolean delete(int id) {
        T object = read(id);
        if (object != null) {
            list.remove(object);
            return true;
        }
        return false;
    }
    
}
