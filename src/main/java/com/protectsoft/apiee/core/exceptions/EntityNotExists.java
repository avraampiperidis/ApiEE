package com.protectsoft.apiee.core.exceptions;

/**
 *
 */
public class EntityNotExists extends RuntimeException {
    
    public EntityNotExists() {}

    public EntityNotExists(Long id, String entitySimpleName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
