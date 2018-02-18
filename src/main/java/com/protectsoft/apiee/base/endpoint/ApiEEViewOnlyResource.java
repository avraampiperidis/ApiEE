
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.ApiEEFacade;
import com.protectsoft.apiee.base.entities.BaseEntity;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <T>
 */
public abstract class ApiEEViewOnlyResource<T extends BaseEntity> extends 
        ApiEEResource <T>  {

    public ApiEEViewOnlyResource(ApiEEFacade<T> service) {
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
