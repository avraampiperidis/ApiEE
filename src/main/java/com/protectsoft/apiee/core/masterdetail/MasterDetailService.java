package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.boundary.ApiFacade;
import com.protectsoft.apiee.base.entities.BaseEntity;

/**
 *
 */
public class MasterDetailService {
    
    public MasterDetailService() {
    }
    
    public <M extends BaseEntity,D extends BaseEntity> void addDetail(Class<M> aClass, Class<D> aClass0, MasterDetailFunction<M, D> masterDetailFunction,ApiFacade<D> detailService, MoveOption moveOption) {
    }
}
