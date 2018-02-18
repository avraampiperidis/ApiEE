
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.interfaces.IReposirotyResource;
import com.protectsoft.apiee.base.entities.BaseEntity;

/**
 *
 * @param <M> Master
 * @param <D> Detail
 */
public abstract class ApiEESubResource<M extends BaseEntity, D  extends BaseEntity> 
        extends BaseSubResource<M, D>
        implements IReposirotyResource<D> {

}
