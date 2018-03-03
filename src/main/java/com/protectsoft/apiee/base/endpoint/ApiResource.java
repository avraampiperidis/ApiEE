
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <T> .
 */
public abstract class ApiResource<T extends BaseEntity> extends BaseResource<T> {
    
    
    public ApiResource(Api<T> service) {
       super(service);
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    @Override
    public List<T> findAll() {
        return super.findAll();
    }
    
    
    @Override
    public List<T> search(UriInfo ui, ContainerRequestContext ctx, JsonObject search_clauses) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
