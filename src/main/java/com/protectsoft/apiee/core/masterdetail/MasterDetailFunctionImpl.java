package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;

/**
 *
 * @author Abraham Piperidis
 * @param <M>
 * @param <D>
 */
class MasterDetailFunctionImpl <M extends BaseEntity,D extends BaseEntity> implements MasterDetailFunction<M, D> {
    
    private final OneToManyFunction<M,D> oneToManyFunction;
    private final OneToOneFunction<M,D> oneToOneFunction;
    private final ManyToManyFunction<M,D> manyToManyFunction;
    
    public MasterDetailFunctionImpl(OneToManyFunction<M,D> detailsFunction,
            OneToOneFunction<M,D> detailFunction,ManyToManyFunction<M,D> manyToManyFunction) {
        this.oneToManyFunction = detailsFunction;
        this.oneToOneFunction = detailFunction;
        this.manyToManyFunction = manyToManyFunction;
    }
    
    @Override
    public List<D> getDetails(M master){
       return oneToManyFunction.getDetails(master);
    }
    
    @Override
    public D getDetail(M master){
        return oneToOneFunction.getDetail(master);
    };
    
    @Override
    public void setMaster(M master,D detail){
        manyToManyFunction.setMaster(master, detail);
    };
    
    @Override
    public void addMaster(M master,D detail) {
        manyToManyFunction.addMaster(master, detail);
    }
}
