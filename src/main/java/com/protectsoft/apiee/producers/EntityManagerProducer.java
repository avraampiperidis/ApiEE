
package com.protectsoft.apiee.producers;

import com.protectsoft.apiee.annotations.NamedDataSource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Override methods for user defined producers
 */
@ApplicationScoped
public class EntityManagerProducer {
    
    @PersistenceContext
    private EntityManager em;
    
    public EntityManagerProducer() {
    }
    
    @Produces
    @NamedDataSource
    @RequestScoped
    EntityManager getEntityManagerDefault() {
        return em;
    }
    
    //issue
    //https://developer.jboss.org/thread/179526
    public void close(@Disposes @NamedDataSource EntityManager em) {
        //em.close();
    }
}
