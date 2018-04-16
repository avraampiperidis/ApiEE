package com.protectsoft.apiee.base.endpoint.interfaces;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Abraham Piperidis
 * @param <T>
 */
public interface ISpringResource<T extends BaseEntity> extends IResource<T> {
    Object createEntity(T entity);
    List<T> search(HttpServletRequest request,JsonObject search);
    @Override
    ResponseEntity<T> edit(Long id, T entity);
}
