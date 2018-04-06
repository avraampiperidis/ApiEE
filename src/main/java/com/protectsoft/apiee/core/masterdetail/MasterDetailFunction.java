package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;

/**
 *
 * @author piper
 * @param <M>
 * @param <D>
 */
public interface MasterDetailFunction<M extends BaseEntity,D extends BaseEntity> extends IMasterDetailRelation<M,D>{
    
    @Override
    default List<D> getDetails(M master){return null;};
    
    @Override
    default D getDetail(M master){return null;};
    
    @Override
    default void setMaster(M master,D detail){};
    
    @Override
    default void addMaster(M master,D detail){};

    @Override
    default void addDetail(M master, D detail){};

    @Override
    default void setDetail(M master, D detail){};
    
    @Override
    default void removeDetail(M master,D detail){};
    
}
