
package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.interfaces.IRepository;
import com.protectsoft.apiee.base.interfaces.IValidation;
import com.protectsoft.apiee.core.CountedList;
import com.protectsoft.apiee.core.annotations.NamedDataSource;
import com.protectsoft.apiee.core.exceptions.EntityException;
import com.protectsoft.apiee.core.exceptions.EntityNotExists;
import com.protectsoft.apiee.core.masterdetail.MasterDetailFunction;
import com.protectsoft.apiee.core.masterdetail.MasterDetailService;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @param <T>
 */
public abstract class ApiFacade<T extends BaseEntity> extends Context implements IRepository<T> , IValidation<T,T> {
       
    @Inject
    @NamedDataSource        
    private EntityManager em;
    
    private final  Class<T> entityClass;
    private final MasterDetailService masterDetailService;
    
    public ApiFacade(Class clazz) {
        this.entityClass = clazz;
        masterDetailService = new MasterDetailService();
    }
    
    @Override
    protected ApiFacade<T> getService() {
        return this;
    }

    public void addDetail(Class<T> masterClass, Class<T> detailClass, MasterDetailFunction<T, T> masterDetailFunction, MoveOption moveOption) {
        if(!masterClass.equals(detailClass)) {
            throw new RuntimeException("master and detail class must be equals, otherwise provide the facade service of the detail class");
        }
        addDetail(masterClass, detailClass, masterDetailFunction,this,moveOption);
    }
     
    public void addDetail(Class<T> aClass, Class<T> aClass0, MasterDetailFunction<T, T> masterDetailFunction, ApiFacade<T> detailService, MoveOption moveOption) {
        this.masterDetailService.<T,T>addDetail(aClass, aClass0, masterDetailFunction,detailService, moveOption);
    }

    
    
    /**
     * @return the em
     */
    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * @return the entityClass
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }
    
    
    
    @Override
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
    
    @Override
    public void create(T entity) {
        validate(entity);
        em.persist(entity);
    }
     
    
    @Override
    public T update(T entity) {
        validate(entity);
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public T find(Long id) {
        return em.find(entityClass, id);
    }
     
    
    @Override
    public void validate(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = (Validator) factory.getValidator();
        Set<ConstraintViolation<T>> cv = validator.validate(entity);
        if (!cv.isEmpty()) { 
           throw new ConstraintViolationException(cv);
        }

        if(!entity.isValid()) {
            throw new ConstraintViolationException("Entity validation failed", null);
        }
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
        return em.merge(dto);
    }
    
    
    public List<T> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        return em.createQuery(cq.select(cq.from(entityClass)))
                .setFirstResult(range[0])
                .setMaxResults(range[1] - range[0] + 1)
                .getResultList();
    }
    
    public CountedList<T> search(JsonObject search) {
        return null;
    }
    
    
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(em.getCriteriaBuilder().count(cq.from(entityClass)));
        return ((Long) em.createQuery(cq).getSingleResult()).intValue();
    }
    
    
    @Override
    public void validateUpdate(T db,T dao) {
    }
    
    
    public String getEntitySimpleName() {
        return entityClass.getSimpleName();
    }

}
