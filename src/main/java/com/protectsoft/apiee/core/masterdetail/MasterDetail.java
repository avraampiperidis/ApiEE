package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;

/**
 *
 * @author piper
 * @param <M>
 * @param <D>
 */
public class MasterDetail<M extends BaseEntity,D extends BaseEntity> implements IMasterDetailRelation<M,D>  {
    
    private Class<M> masterClass;
    private Class<D> childClass;
    private MoveOption moveOption;
    private String selector = "";
    private MasterDetailFunction<M,D> function;
    
    
    public MasterDetail(Class<M> masterClass, Class<D> detailClass,MoveOption mo) {
        this.masterClass = masterClass;
        this.childClass = detailClass;
        this.moveOption = mo;
    }
    
    
    public MasterDetail(Class<M> masterClass, Class<D> detailClass,MasterDetailFunction<M,D> function,MoveOption mo) {
        this(masterClass,detailClass,mo);
        this.function = function;
    }
    
    public MasterDetail(Class<M> masterClass, Class<D> detailClass,MasterDetailFunction<M,D> function,MoveOption mo,String selector) {
        this(masterClass,detailClass,function,mo);
        this.selector = selector;
    }
    
   
    
    @Override
    public List<D> getDetails(M master) {
        return this.function.getDetails(master);
    }
    
    @Override
    public D getDetail(M master) {
        return this.function.getDetail(master);
    }
    
    @Override
    public void setMaster(M master,D detail) {
        this.function.setMaster(master, detail);
    }
    
    @Override
    public void addMaster(M master,D detail) {
        this.function.addMaster(master, detail);
    }
    
    @Override
    public void setDetail(M master, D detail) {
        this.function.setDetail(master, detail);
    }

    @Override
    public void addDetail(M master, D detail) {
        this.function.addDetail(master, detail);
        this.getDetails(master).add(detail);
    }
    
    @Override
    public void removeDetail(M master,D detail) {
        this.function.getDetails(master).remove(detail);
        this.setMaster(null,detail);
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

    
    public RelationType getRelationType() {
        if(this.function instanceof OneToOneFunction) {
            return RelationType.ONE_TO_ONE;
        } else if(this.function instanceof OneToManyFunction) {
            return RelationType.ONE_TO_MANY;
        } else if(this.function instanceof ManyToManyFunction) {
            return RelationType.MANY_TO_MANY;
        }
        throw new RuntimeException("Wrong instance type of MasterDetailFunction");
    }

   
}
