
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <T> .
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class ApiResource<T extends BaseEntity> extends BaseResource<T> {
    
    
    public ApiResource(Api<T> service) {
       super(service);
    }
    
    @GET   
    @Override
    public List<T> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("{id}")
    @Override
    public T find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @POST
    @Override
    public Response create(UriInfo ui, T entity) {
        return super.create(ui, entity); 
    }

    @PUT
    @Path("{id}")
    @Override
    public Response edit(Long id, T entity) {
        return super.edit(id, entity); 
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response remove(Long id) {
        return super.remove(id); 
    }

    @GET
    @Path("{from}/{to}")
    @Override
    public List<T> findRange(@PathParam("from") Integer from,@PathParam("to") Integer to) {
        return super.findRange(from, to); 
    }
    
    @POST
    @Override
    public List<T> search(UriInfo ui, ContainerRequestContext ctx, JsonObject search_clauses) {
        return super.search(ui, ctx, search_clauses);
    }
    
}
