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
    
    private final DetailsFunction<M,D> detailsFunction;
    private final DetailFunction<M,D> detailFunction;
    private final MasterFunction<M,D> masterFunction;
    
    public MasterDetailFunctionImpl(DetailsFunction<M,D> detailsFunction,
            DetailFunction<M,D> detailFunction,MasterFunction<M,D> masterFunction) {
        this.detailsFunction = detailsFunction;
        this.detailFunction = detailFunction;
        this.masterFunction = masterFunction;
    }
    
    @Override
    public List<D> getDetails(M master){
       return detailsFunction.getDetails(master);
    }
    
    @Override
    public D getDetail(M master){
        return detailFunction.getDetail(master);
    };
    
    @Override
    public void setMaster(M master,D detail){
        masterFunction.setMaster(master, detail);
    };
    
}
