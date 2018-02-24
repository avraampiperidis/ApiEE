
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.interfaces.IReposirotyResource;
import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <M> Master
 * @param <D> Detail
 */
public abstract class ApiSubResource<M extends BaseEntity, D  extends BaseEntity> 
        extends BaseSubResource<M, D>
        implements IReposirotyResource<D> {
    
    public ApiSubResource(Api<M> api,Long id) {
    }

    @Override
    public Object countREST() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object create(UriInfo ui, D entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object edit(Long id, D entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public D find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<D> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<D> findRange(Integer from, Integer to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<D> search(UriInfo ui, ContainerRequestContext ctx, JsonObject search_clauses) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
