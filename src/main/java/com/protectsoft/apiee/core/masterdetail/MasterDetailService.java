package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MasterDetailService {
    
    private final List<MasterDetail<?,?>> relations;
    
    public MasterDetailService() {
        this.relations = new ArrayList<>();
    }
    
    public <M extends BaseEntity,D extends BaseEntity> void addRelation(Class masterClass,Class detailClass,MasterDetailFunction<M,D> function,MoveOption option) {
        MasterDetail<M,D> masterDetalRelation = new MasterDetail<>(masterClass,detailClass,function,option);
        this.relations.add(masterDetalRelation);
    }
    
    
}
