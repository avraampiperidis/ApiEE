package com.protectsoft.apiee.base.core;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.annotations.NamedDataSource;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Abraham Piperidis
 */
public class RepoAccess  {
       
    @Inject
    @NamedDataSource        
    private EntityManager em;
        
    RepoAccess() {
    }
    
    public EntityManager getEntityManager() {
        return em;
    }
    
    
    protected <T extends BaseEntity> List<T> findAll(Class<T> entityClass) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
    
    
    protected <T extends BaseEntity> void create(T entity) {
        em.persist(entity);
    }
    
    
    protected <T extends BaseEntity> T update(T entity) {
        return em.merge(entity);
    }
    
    
    protected <T extends BaseEntity> void remove(T entity) {
        em.remove(em.merge(entity));
    }
    
    protected <T extends BaseEntity> T find(Class<T> clazz,Long id) {
        return em.find(clazz, id);
    }
    
    protected <T extends BaseEntity> List<T> findRange(int[] range,Class<T> clazz) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        return em.createQuery(cq.select(cq.from(clazz)))
                .setFirstResult(range[0])
                .setMaxResults(range[1] - range[0] + 1)
                .getResultList();
    }
    
    protected <T extends BaseEntity> int count(Class<T> clazz) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(em.getCriteriaBuilder().count(cq.from(clazz)));
        return ((Long) em.createQuery(cq).getSingleResult()).intValue();
    }

    
    
}
