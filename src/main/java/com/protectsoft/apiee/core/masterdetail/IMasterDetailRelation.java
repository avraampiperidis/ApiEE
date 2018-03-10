package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;

/**
 *
 * @author piper
 * @param <M> Master
 * @param <D> Detail
 */
public interface IMasterDetailRelation<M extends BaseEntity,D extends BaseEntity> {
    
    List<D> getDetails(M master);
    
    D getDetail(M master);
    
    void setMaster(M master,D detail);
    
    void setDetail(M master,D detail);
    
    void addDetail(M master,D detail);
    
    void removeDetail(M master,D detail);
}
