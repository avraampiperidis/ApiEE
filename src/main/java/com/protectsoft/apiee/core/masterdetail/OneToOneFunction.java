package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;

/**
 *
 * @author Abraham Piperidis
 * @param <M>
 * @param <D>
 */
public interface OneToOneFunction<M extends BaseEntity,D extends BaseEntity> extends MasterDetailFunction<M,D> {
    
    @Override
    D getDetail(M master);

    @Override
    void setMaster(M master,D detail);
}
