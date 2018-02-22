
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.ApiFacade;
import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @param <T> .
 */
public abstract class ApiResource<T extends BaseEntity> extends BaseResource<T> {
    
    
    public ApiResource(ApiFacade<T> service) {
       super(service);
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    @Override
    public List<T> findAll() {
        return super.findAll();
    }
    
}
