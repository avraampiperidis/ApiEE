
package com.protectsoft.apiee.base.core;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.interfaces.IRepository;
import com.protectsoft.apiee.base.interfaces.IValidation;
import com.protectsoft.apiee.util.CountedList;
import com.protectsoft.apiee.core.exceptions.EntityException;
import com.protectsoft.apiee.core.exceptions.EntityNotExists;
import java.util.List;
import java.util.Objects;
import javax.json.JsonObject;



/**
 * @author Avraam Piperidis
 * @param <T>
 */
public abstract class Api<T extends BaseEntity> extends Context<T>  implements IRepository<T> , IValidation<T,T> {
    
    
    public Api(Class<T> clazz) {
        super(clazz);
    }
    
    
    @Override
    public Api<T> getService() {
        return this;
    }
   
    @Override
    final void setParent() {
        setParent(this);
    }

    
    @Override
    public List<T> findAll() {
        return getRepo().findAll(getEntityClass());
    }
    
    @Override
    public void create(T entity) {
        validate(entity);
        getRepo().create(entity);
    }
     
    
    @Override
    public T update(T entity) {
        validate(entity);
        return  getRepo().update(entity);
    }

    @Override
    public void delete(T entity) {
        getRepo().remove(entity);
    }

    @Override
    public T find(Long id) {
        return getRepo().find(getEntityClass(), id);
    }
     
    
    @Override
    public void validate(T entity) {
        Validator.validate(entity);
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
        return getRepo().update(dto);
    }
    
    
    public List<T> findRange(int[] range) {
        return getRepo().findRange(range, getEntityClass());
    }
    
    
    public CountedList<T> search(JsonObject search) {
        return null;
    }
    
    
    public int count() {
        return getRepo().count(getEntityClass());
    }
    
    
    @Override
    public void validateUpdate(T db,T dao) {
        Validator.validateUpdate(db,dao);
    }
    
    
}
