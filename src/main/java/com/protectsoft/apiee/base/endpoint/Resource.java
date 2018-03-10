package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.core.Relation;
import com.protectsoft.apiee.base.entities.BaseEntity;
import java.net.URI;
import javax.ws.rs.core.UriInfo;

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
        this.rule();
    }
    
    public Api<T> getService() {
        return this.service;
    }
    
    public Relation<T> getRelation() {
        return this.relation;
    }
    
    
    private void rule() {
        if(service.find(relation.getParentId())== null) {
            throw new RuntimeException();
        }
    }
    
    
    public <D extends BaseEntity> URI getNewPath(UriInfo ui,D entity) {
        return ui.getAbsolutePathBuilder().path(entity.getId().toString()).build();
    }
}
