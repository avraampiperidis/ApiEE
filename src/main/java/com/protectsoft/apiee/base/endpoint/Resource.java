package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.boundary.Relation;
import com.protectsoft.apiee.base.entities.BaseEntity;

/**
 *
 * @author Abraham Piperidis
 * @param <T>
 */
public abstract class Resource<T extends BaseEntity>   {
    
    private final Api<T> service;
    private Relation<T> relation;
    
    Resource(Api<T> service) {
        this.service = service;
    }
    
    Resource(Api<T> service,Relation<T> relation) {
        this(service);
        this.relation = relation;
    }
    
    public Api<T> getService() {
        return this.service;
    }
    
    public Relation<T> getRelation() {
        return this.relation;
    }
}
