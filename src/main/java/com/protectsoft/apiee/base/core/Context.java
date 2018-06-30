package com.protectsoft.apiee.base.core;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.util.ApiUtils;
import com.protectsoft.apiee.core.masterdetail.MasterDetail;
import com.protectsoft.apiee.core.masterdetail.MasterDetailFunction;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Avraam Piperidis
 * @param <T>
 */
public abstract class Context<T extends BaseEntity> implements IContext<T> {
      
    @Inject
    private RepoAccess repo; 
            
    private final Class<T> entityClass;
    
    private final List<Pair<MasterDetail,Api<?>>> childs;
    private Api<T> parent;
    
    Context(Class<T> clazz) {     
        if(!ApiUtils.isClassInstance(BaseEntity.class,clazz)) {
            throw new RuntimeException("Class must be type of BaseEntity or any subclass");
        }
        this.childs = new ArrayList<>();
        this.setParent();
        this.entityClass = clazz;
    }
    
    abstract void setParent();
    
    @Override
    public abstract Api<T> getService();
    
    @Override
    public Api<T> getParent() {
        return parent;
    }
    
    public RepoAccess getRepo() {
        return this.repo;
    }
    
    public Class<T> getEntityClass() {
        return this.entityClass;
    }
    
    public String getEntitySimpleName() {
        return entityClass.getSimpleName();
    }
    
    /**
     * @return the em
     */
    public EntityManager getEntityManager() {
        return repo.getEntityManager();
    }
    
    
    <M extends BaseEntity> void setParent(Api<M> parent) {
        this.setParent(null,(Api<T>) parent);
    }

    
    
    public <D extends BaseEntity> void addChildDetail(Class<T> masterClass, Class<D> detailClass, 
            MasterDetailFunction<T, D> masterDetailFunction, 
            Api<D> detailService, MoveOption moveOption) {
        this.addChild(new MasterDetail<>(masterClass,detailClass,masterDetailFunction,moveOption),detailService);
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
    
    
    
}
