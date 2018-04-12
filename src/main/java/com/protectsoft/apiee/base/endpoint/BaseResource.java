
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.exceptions.EntityNotExists;
import com.protectsoft.apiee.core.exceptions.RequestException;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

/**
 *
 * @param <T> .
 */
public abstract class BaseResource<T extends BaseEntity>  extends Resource<T> {
    
        
    public BaseResource(Api<T> t) {
        super(t);
    }
    
    public List<T> findAll() {
        return getService().findAll();
    }
    
    public  T create(T entity) {
        if(entity == null) {
            throw new RequestException();
        }
        getService().create(entity);
        return entity;
    }
    
    
    public Response edit(Long id, T entity) {
        if(entity == null) {
            throw new RequestException();
        }
        return Response.ok().entity(getService().update(id, entity)).build();
    }
    
    
    public Response remove(Long id) {
        T o = getService().find(id);
        if(o == null) {
            throw new EntityNotExists(id, getService().getEntitySimpleName());
        }
        getService().delete(o);
        return Response.noContent().build();
    }
    
    
    public T find(Long id) {
        T entity = getService().find(id);
        if(entity == null) {
            throw new EntityNotExists(id, getService().getEntitySimpleName());
        }
        return entity;
    }
    
    
    public List<T> findRange(Integer from, Integer to) {
        return getService().findRange(new int[]{from, to});
    }

    public Integer count() {
        return getService().count();
    }
    
    
    public List<T> search(ContainerRequestContext ctx, JsonObject search_clauses) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
   

}
