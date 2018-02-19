package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;

/**
 *
 * @author piper
 * @param <M>
 * @param <D>
 */
public class MasterDetail<M extends BaseEntity,D extends BaseEntity> implements IMasterDetailRelation<M,D> {
    
    private Class<M> masterClass;
    private Class<D> childClass;
    private MoveOption moveOption;
    private String selector;
    
    private MasterDetailFunction<M,D> function;
    
    public MasterDetail() {}
    
    
    public MasterDetail(Class<M> masterClass, Class<D> detailClass,MasterDetailFunction<M,D> function,MoveOption mo) {
        this.masterClass = masterClass;
        this.childClass = detailClass;
        this.moveOption = mo;
        this.function = function;
    }

    @Override
    public List<D> getDetails(M master) {
        return null;
    }

    @Override
    public D getDetail(M master) {
        return null;
    }

    @Override
    public void setMaster(M master, D detail) {
    }

    /**
     * @return the function
     */
    public MasterDetailFunction<M,D> getFunction() {
        return function;
    }

    
}
