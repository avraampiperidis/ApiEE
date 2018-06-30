
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.exceptions.EntityNotExists;
import com.protectsoft.apiee.core.exceptions.RequestException;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;

/**
 *
 * @param <T> .
 */
public abstract class BaseResource<T extends BaseEntity>  extends Resource<T> {
    
        
    public BaseResource(Api<T> t) {
        super(t);
    }
    
    public List<T> baseFindAll() {
        return getService().findAll();
    }
    
    public  T baseCreate(T entity) {
        if(entity == null) {
            throw new RequestException();
        }
        getService().create(entity);
        return entity;
    }
    
    
    public T baseEdit(Long id, T entity) {
        if(entity == null) {
            throw new RequestException();
        }
        return getService().update(id, entity);
    }
    
    
    public void baseRemove(Long id) {
        T o = getService().find(id);
        if(o == null) {
            throw new EntityNotExists(id, getService().getEntitySimpleName());
        }
        getService().delete(o);
    }
    
    
    public T baseFind(Long id) {
        T entity = getService().find(id);
        if(entity == null) {
            throw new EntityNotExists(id, getService().getEntitySimpleName());
        }
        return entity;
    }
    
    
    public List<T> baseFindRange(Integer from, Integer to) {
        return getService().findRange(new int[]{from, to});
    }

    public Integer baseCount() {
        return getService().count();
    }
    
    
    public List<T> baseSearch(ContainerRequestContext ctx, JsonObject search_clauses) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
   

}
