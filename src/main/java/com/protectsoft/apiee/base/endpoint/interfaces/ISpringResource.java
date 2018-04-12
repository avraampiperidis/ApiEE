package com.protectsoft.apiee.base.endpoint.interfaces;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Abraham Piperidis
 * @param <T>
 */
public interface ISpringResource<T extends BaseEntity> extends IResource<T> {
    Object create(HttpServletRequest request,T entity);
    List<T> search(HttpServletRequest request,JsonObject search);
}
