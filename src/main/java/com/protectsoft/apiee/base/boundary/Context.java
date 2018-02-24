package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.masterdetail.MasterDetail;
import com.protectsoft.apiee.core.masterdetail.MasterDetailFunction;
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

    private final List<Pair<MasterDetail,Api<T>>> childs;
    private Api<T> parent;
    
    public Context() {     
        this.childs = new ArrayList<>();
        this.setParent();
    }
    
    abstract public void setParent();
    
    @Override
    public abstract Api<T> getService();
    
    @Override
    public Api<T> getParent() {
        return parent;
    }
    
    
    @Override
    public void setParent(Api<T> parent) {
        this.setParent(null, parent);
    }
    
    
    @Override
    public void addChild(Api<T> child) {
        this.addChild(null,child);    
    }
    
    
    public void addDetail(Class<T> masterClass, Class<T> detailClass, MasterDetailFunction<T, T> masterDetailFunction, Api<T> detailService, MoveOption moveOption) {
        this.addChild(new MasterDetail<T,T>(masterClass,detailClass,masterDetailFunction,moveOption),detailService);
    }
    
    
    /**
     * @return the child services
     */
    @Override
    public List<Pair<MasterDetail,Api<T>>> getChilds() {
        return childs;
    }

    
    /**
     * 
     * @param clazz 
     * @param targetClass 
     * @return 
     */
    public boolean isClassInstance(Class clazz,Class targetClass) {
        if(clazz.equals(targetClass)) {
            return true;
        }
        if(clazz.equals(Object.class)) {
            return false;
        }
        return isClassInstance(clazz,targetClass.getSuperclass());
    }
    
    
    private void addChild(MasterDetail<T,T> mDetail,Api<T> child) {
        child.setParent((Api<T>)this);
        if(!this.childs.stream().anyMatch(x->Objects.equals(x.getApi(),child))) {
            this.childs.add(new Pair<>(mDetail,child));
        }     
    }
    
    private void setParent(MasterDetail<T,T> mDetail,Api<T> parent) {
        this.parent = parent;
        if(!parent.equals(this)) {
            if(!this.parent.getChilds().stream().anyMatch(x->Objects.equals(x.getApi(),(Api<T>)this))) {
                this.parent.getChilds().add(new Pair<>(mDetail,(Api<T>)this));
            }
        }
    }
    
}
