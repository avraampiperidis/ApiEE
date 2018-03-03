
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.interfaces.IReposirotyResource;
import com.protectsoft.apiee.core.CountedList;
import com.protectsoft.apiee.core.exceptions.EntityNotExists;
import com.protectsoft.apiee.core.exceptions.RequestException;
import java.net.URI;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <T> .
 */
public abstract class BaseResource<T extends BaseEntity>  extends Resource<T>  implements IReposirotyResource<T>  {
    
        
    public BaseResource(Api<T> t) {
        super(t);
    }
    
    @Override
    public List<T> findAll() {
        return getService().findAll();
    }
    
    @Override
    public  Response create(UriInfo ui, T entity) {
        if(entity == null) {
            throw new RequestException();
        }
        getService().create(entity);
        URI uri = ui.getAbsolutePathBuilder().path(entity.getId().toString()).build();
        return Response
                .created(uri)
                .entity(entity)
                .build();
    }
    
    
    @Override
    public Response edit(Long id, T entity) {
        if(entity == null) {
            throw new RequestException();
        }
        return Response.ok().entity(getService().update(id, entity)).build();
    }
    
    
    @Override
    public Response remove(Long id) {
        T o = getService().find(id);
        if(o == null) {
            throw new EntityNotExists(id, getService().getEntitySimpleName());
        }
        getService().delete(o);
        return Response.noContent().build();
    }
    
    
    @Override
    public T find(Long id) {
        T entity = getService().find(id);
        if(entity == null) {
            throw new EntityNotExists(id, getService().getEntitySimpleName());
        }
        return entity;
    }
    
    
    @Override
    public List<T> findRange(Integer from, Integer to) {
        return getService().findRange(new int[]{from, to});
    }

    @Override
    public String countREST() {
        return String.valueOf(getService().count());
    }
    
    public CountedList<T> search(JsonObject searchClauses) {
       return getService().search(searchClauses);
    }
   
        
 

}
