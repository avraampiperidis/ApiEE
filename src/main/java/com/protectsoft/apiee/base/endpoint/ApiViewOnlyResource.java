
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.ApiFacade;
import com.protectsoft.apiee.base.entities.BaseEntity;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <T>
 */
public abstract class ApiViewOnlyResource<T extends BaseEntity> extends 
        ApiResource <T>  {

    public ApiViewOnlyResource(ApiFacade<T> service) {
        super(service);
    }

    @Override
    public final Response remove(Long id) {
        throw new NotAllowedException("");
    }

    @Override
    public final Response edit(Long id, T entity) {
        throw new NotAllowedException("");
    }

    @Override
    public final Response create(UriInfo ui, T entity) {
        throw new NotAllowedException("");
    }
    
        
}
