package com.protectsoft.apiee.core;

import com.protectsoft.apiee.entities.BaseEntity;

/**
 *
 * @author Abraham Piperidis
 * @param <T>
 */
public class ReadOnlyApi<T extends BaseEntity> extends Api<T> {
    
    public ReadOnlyApi(Class<T> clazz) {
        super(clazz);
    }
    
    @Override
    final public void create(T entity) {
        throw new RuntimeException();
    }
    
    @Override
    final public T update(T entity) {
        throw new RuntimeException();
    }
    
    @Override
    final public void delete(T entity) {
        throw new RuntimeException();
    }
    
}
