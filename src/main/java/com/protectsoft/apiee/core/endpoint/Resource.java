package com.protectsoft.apiee.core.endpoint;

import com.protectsoft.apiee.core.Api;
import com.protectsoft.apiee.core.Relation;
import com.protectsoft.apiee.entities.BaseEntity;
import java.net.URI;
import javax.ws.rs.core.UriInfo;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    
    
    public <D extends BaseEntity> URI getNewPathSpring(D entity) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(entity.getId().toString()).toUri();
    }
}
