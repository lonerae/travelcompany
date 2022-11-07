package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.model.PersistentClass;
import java.util.List;

public interface Repository<T extends PersistentClass> {
    
    int create(T t);
    T read(int id);
    List<T> read();
    boolean delete(int id);
    
}
