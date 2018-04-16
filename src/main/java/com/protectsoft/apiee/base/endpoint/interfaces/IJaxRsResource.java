package com.protectsoft.apiee.base.endpoint.interfaces;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Abraham Piperidis
 * @param <T>
 */
public interface IJaxRsResource<T extends BaseEntity> extends IResource<T> {
    Object create(UriInfo ui, T entity);
    List<T> search(ContainerRequestContext ctx, JsonObject search_clauses);
    @Override
    Response edit(Long id, T entity);
}
