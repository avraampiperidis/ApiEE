package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;

/**
 *
 * @author piper
 * @param <M>
 * @param <D>
 */
public class MasterDetail<M extends BaseEntity,D extends BaseEntity>  {
    
    private Class<M> masterClass;
    private Class<D> childClass;
    private MoveOption moveOption;
    private String selector = "";
    private MasterDetailFunction<M,D> function;
    
    public MasterDetail() {}
    
    
    public MasterDetail(Class<M> masterClass, Class<D> detailClass,MasterDetailFunction<M,D> function,MoveOption mo) {
        this.masterClass = masterClass;
        this.childClass = detailClass;
        this.moveOption = mo;
        this.function = function;
    }
    
    public MasterDetail(Class<M> masterClass, Class<D> detailClass,MasterDetailFunction<M,D> function,MoveOption mo,String selector) {
        this(masterClass,detailClass,function,mo);
        this.selector = selector;
    }


    /**
     * @return the function
     */
    public MasterDetailFunction<M,D> getFunction() {
        return function;
    }

    /**
     * @return the masterClass
     */
    public Class<M> getMasterClass() {
        return masterClass;
    }

    /**
     * @return the childClass
     */
    public Class<D> getChildClass() {
        return childClass;
    }

    /**
     * @return the moveOption
     */
    public MoveOption getMoveOption() {
        return moveOption;
    }

    /**
     * @return the selector
     */
    public String getSelector() {
        return selector;
    }

    
}
