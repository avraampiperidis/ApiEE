
package com.protectsoft.apiee.base.interfaces;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @param <T>
 */
public interface IResource<T extends BaseEntity> {

    Object count();

    Object create(UriInfo ui, T entity);

    Object edit(Long id, T entity);

    T find(Long id);

    List<T> findAll();

    List<T> findRange(Integer from,Integer to);

    Object remove(Long id);

    List<T> search(ContainerRequestContext ctx, JsonObject search_clauses);

}
