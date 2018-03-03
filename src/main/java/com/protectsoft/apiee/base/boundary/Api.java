
package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.interfaces.IRepository;
import com.protectsoft.apiee.base.interfaces.IValidation;
import com.protectsoft.apiee.core.ApiUtils;
import com.protectsoft.apiee.core.CountedList;
import com.protectsoft.apiee.core.exceptions.EntityException;
import com.protectsoft.apiee.core.exceptions.EntityNotExists;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;



/**
 * @author Avraam Piperidis
 * @param <T>
 */
public abstract class Api<T extends BaseEntity> extends Context<T>  implements IRepository<T> , IValidation<T,T> {
    
    @Inject
    private RepoAccess repo;
    
    @Inject
    private Validator validator;
        
    private final  Class<T> entityClass;
    
    public Api(Class<T> clazz) {
        if(!ApiUtils.isClassInstance(BaseEntity.class,clazz)) {
            throw new RuntimeException("Class must be type of BaseEntity or any subclass");
        } 
        this.entityClass = clazz;
    }
    
    
    @Override
    public Api<T> getService() {
        return this;
    }
   
    @Override
    final void setParent() {
        setParent(this);
    }

    
    /**
     * @return the em
     */
    public EntityManager getEntityManager() {
        return repo.getEntityManager();
    }

    /**
     * @return the entityClass
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }
    
    
    
    @Override
    public List<T> findAll() {
        return repo.findAll(entityClass);
    }
    
    @Override
    public void create(T entity) {
        validate(entity);
        repo.create(entity);
    }
     
    
    @Override
    public T update(T entity) {
        validate(entity);
        return repo.update(entity);
    }

    @Override
    public void delete(T entity) {
        repo.remove(entity);
    }

    @Override
    public T find(Long id) {
        return repo.find(entityClass, id);
    }
     
    
    @Override
    public void validate(T entity) {
        validator.validate(entity);
    }
    
    @Override
    public T update(Long id, T dto) {
        T entity = find(id);
        if(entity == null)
            throw new EntityNotExists(id, getEntitySimpleName());
        if(!Objects.equals(id, dto.getId()))
            throw new EntityException(400, 104, "Ασυμφωνία ID.");      
        validateUpdate(entity, dto);
        validate(dto);
        return repo.update(dto);
    }
    
    
    public List<T> findRange(int[] range) {
        return repo.findRange(range, entityClass);
    }
    
    
    public CountedList<T> search(JsonObject search) {
        return null;
    }
    
    
    public int count() {
        return repo.count(entityClass);
    }
    
    
    @Override
    public void validateUpdate(T db,T dao) {
        validator.validateUpdate(db,dao);
    }
    
    
    public String getEntitySimpleName() {
        return entityClass.getSimpleName();
    }

   
    
}
