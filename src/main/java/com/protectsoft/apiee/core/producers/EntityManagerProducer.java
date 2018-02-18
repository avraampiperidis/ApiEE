
package com.protectsoft.apiee.core.producers;

import com.protectsoft.apiee.core.annotations.NamedDataSource;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 */
public class EntityManagerProducer {
    
    @PersistenceContext
    private EntityManager em;
    
    public EntityManagerProducer() {
    }
    
    @Produces
    @NamedDataSource
    EntityManager getEntityManagerDefault() {
        return em;
    }
    
    //issue
    //https://developer.jboss.org/thread/179526
    public void close(@Disposes @NamedDataSource EntityManager em) {
        //em.close();
    }
}
