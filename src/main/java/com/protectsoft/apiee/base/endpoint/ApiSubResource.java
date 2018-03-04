
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.Api;
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
 * @param <M> Master
 * @param <D> Detail
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class ApiSubResource<M extends BaseEntity, D  extends BaseEntity> 
        extends BaseSubResource<M, D>
         {
    
    public ApiSubResource(Long id,Api<M> service,Class<D> childClass) {
        super(id,service,childClass);
    }

    @GET
    @Override
    public Integer count() {
        return super.count();
    }

    @POST
    @Override
    public Response create(UriInfo ui, D entity) {
         return super.create(ui, entity);
    }

    @PUT
    @Path("{id}")
    @Override
    public D edit(@PathParam("id") Long id,D entity) {
        return super.edit(id, entity);
    }

    @GET
    @Path("{id}")
    @Override
    public D find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    public List<D> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Override
    public List<D> findRange(@PathParam("from") Integer from,@PathParam("to") Integer to) {
        return super.findRange(from, to);
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response remove(@PathParam("id") Long id) {
        return (Response) super.remove(id);
    }

    @POST
    @Override
    public List<D> search(UriInfo ui, ContainerRequestContext ctx, JsonObject search_clauses) {
        return super.search(ui, ctx, search_clauses);
    }

}
