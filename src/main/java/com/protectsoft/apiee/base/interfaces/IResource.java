
package com.protectsoft.apiee.base.interfaces;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <T>
 */
public interface IResource<T extends BaseEntity> {

    Object count();

    Object create(@Context UriInfo ui, T entity);

    Object edit(@PathParam("id") Long id, T entity);

    T find(@PathParam("id") Long id);

    List<T> findAll();

    List<T> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to);

    Object remove(@PathParam("id") Long id);

    List<T> search(@Context UriInfo ui, @Context ContainerRequestContext ctx, JsonObject search_clauses);

}
