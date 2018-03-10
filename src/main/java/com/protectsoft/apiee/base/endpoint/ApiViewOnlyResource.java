
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.entities.BaseEntity;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <T>
 */
public abstract class ApiViewOnlyResource<T extends BaseEntity> extends 
        ApiResource <T>  {

    public ApiViewOnlyResource(Api<T> service) {
        super(service);
    }

    @Override
    public final Response remove(@PathParam("id") Long id) {
        throw new NotAllowedException("");
    }

    @Override
    public final Response edit(@PathParam("id") Long id, T entity) {
        throw new NotAllowedException("");
    }

    @Override
    public final Response create(@Context UriInfo ui, T entity) {
        throw new NotAllowedException("");
    }
    
        
}
