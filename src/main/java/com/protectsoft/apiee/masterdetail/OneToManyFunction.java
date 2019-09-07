package com.protectsoft.apiee.masterdetail;

import com.protectsoft.apiee.entities.BaseEntity;
import java.util.List;

/**
 *
 * @author Abraham Piperidis
 * @param <M>
 * @param <D>
 */
public interface OneToManyFunction<M extends BaseEntity,D extends BaseEntity> extends MasterDetailFunction<M,D> {
    
    @Override
    List<D> getDetails(M master);  
    
    @Override
    void setMaster(M master,D detail);
}
