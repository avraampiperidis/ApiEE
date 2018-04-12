
package com.protectsoft.apiee.base.endpoint.interfaces;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;

/**
 *
 * @param <T>
 */
public interface IResource<T extends BaseEntity> {

    Object count();

    Object edit(Long id, T entity);

    T find(Long id);

    List<T> findAll();

    List<T> findRange(Integer from,Integer to);

    Object remove(Long id);

}
