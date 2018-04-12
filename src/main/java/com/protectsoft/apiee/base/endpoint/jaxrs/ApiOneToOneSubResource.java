package com.protectsoft.apiee.base.endpoint.jaxrs;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.endpoint.BaseSubResource;
import com.protectsoft.apiee.base.endpoint.interfaces.IJaxRsResource;
import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Abraham Piperidis
 * @param <M>
 * @param <D>
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class ApiOneToOneSubResource<M extends BaseEntity, D  extends BaseEntity> 
        extends BaseSubResource<M, D> implements IJaxRsResource<D>  {
    
    public ApiOneToOneSubResource(Long id,Api<M> service,Class<D> childClass) {
        super(id,service,childClass);
    }
    
    @GET
    @Path("count")
    @Override
    public Integer count() {
        throw new NotFoundException();
    }
    
    @POST
    @Override
    public Response create(@Context UriInfo ui, D entity) {
        if(super.getDetail() != null){
            throw new EntityExistsException();
        }
        
        return Response
                .created(getNewPath(ui,super.create(entity)))
                .entity(entity)
                .build();
    }
    
    @PUT
    @Path("{id}")
    @Override
    public D edit(@PathParam("id") Long id,D entity) {
        throw new NotFoundException();
    }
    
    @PUT
    public D edit(D entity) {
        D db = super.getDetail();
        return super.edit(db.getId(), entity);
    }
    
    @GET
    @Path("{id}")
    @Override
    public D find(@PathParam("id") Long id) {
        return super.getDetail();
    }
    
    @GET
    public D find() {
        return super.getDetail();
    }

    @Override
    public List<D> findAll() {
        throw new NotFoundException();
    }

    @GET
    @Path("{from}/{to}")
    @Override
    public List<D> findRange(@PathParam("from") Integer from,@PathParam("to") Integer to) {
        throw new NotFoundException();
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response remove(@PathParam("id") Long id) {
        throw new NotFoundException();
    }
    
    @DELETE
    public Response remove() {
        return (Response) super.remove(super.getDetail().getId());
    }

    @POST
    @Path("search")
    @Override
    public List<D> search(@Context ContainerRequestContext ctx, JsonObject search_clauses) {
        throw new NotFoundException();
    }
}
