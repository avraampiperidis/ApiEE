package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.masterdetail.DetailFunction;
import com.protectsoft.apiee.core.masterdetail.DetailsFunction;
import com.protectsoft.apiee.core.masterdetail.MasterDetail;
import com.protectsoft.apiee.core.masterdetail.MasterDetailFunction;
import com.protectsoft.apiee.core.masterdetail.MasterFunction;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Avraam Piperidis
 * @param <T>
 */
public abstract class Context<T extends BaseEntity> implements IContext<T> {

    private final List<Pair<MasterDetail,Api<?>>> childs;
    private Api<T> parent;
    
    public Context() {     
        this.childs = new ArrayList<>();
        this.setParent();
    }
    
    abstract void setParent();
    
    @Override
    public abstract Api<T> getService();
    
    @Override
    public Api<T> getParent() {
        return parent;
    }
    
    
    <M extends BaseEntity> void setParent(Api<M> parent) {
        this.setParent(null,(Api<T>) parent);
    }
    
    
    @Override
    public <D extends BaseEntity> void addChild(Api<D> child) {
        this.addChild(null,child);    
    }
    
    
    public <D extends BaseEntity> void addChildDetail(Class<T> masterClass, Class<D> detailClass, 
            MasterDetailFunction<T, D> masterDetailFunction, 
            Api<D> detailService, MoveOption moveOption) {
        this.addChild(new MasterDetail<>(masterClass,detailClass,masterDetailConstraint(masterDetailFunction),moveOption),detailService);
    }
  
    
    
    public <D extends BaseEntity> void addChildDetail(Class<T> masterClass, Class<D> detailClass, 
            DetailsFunction<T, D> detailsFunction,
            DetailFunction<T,D> detailFunction,
            MasterFunction<T,D> masterFunction,
            Api<D> detailService, MoveOption moveOption) {
        this.addChild(new MasterDetail<>(masterClass,detailClass,detailsFunction,detailFunction,masterFunction,moveOption),detailService);
    }
    
    
    /**
     * @return the child services
     */
    @Override
    public List<Pair<MasterDetail,Api<? extends BaseEntity>>> getChildDetails() {
        return childs;
    }
    
    @Override
    public List<Api<? extends BaseEntity>> getChilds() {
        List<Api<?>> list = new ArrayList<>();
        this.childs.stream().forEach((t) -> {
             list.add(t.getApi());
        });
        return list;
    }

    
    private <D extends BaseEntity> void addChild(MasterDetail<T,D> mDetail,Api<D> child) {
        child.setParent((Api<T>)this);
        if(!this.childs.stream().anyMatch(x->Objects.equals(x.getApi(),child))) {
            this.childs.add(new Pair(mDetail,child));
        }
    }
    
    <D extends BaseEntity> void setParent(MasterDetail<T,D> mDetail,Api<T> parent) {
         this.parent = parent;
    }
    
    
    
    
    private <D extends BaseEntity> MasterDetailFunction<T, D> masterDetailConstraint(MasterDetailFunction<T, D> mf) {
        if(mf instanceof DetailsFunction || mf instanceof DetailFunction || mf instanceof MasterFunction) {
            throw new RuntimeException("MasterDetailFunction cannot be any of the following instances "
                    + "DetailsFunction,DetailFunction,MasterFunction.. use the appropriate method "
                    + "instead");
        } 
        return mf;
    }
    
}
