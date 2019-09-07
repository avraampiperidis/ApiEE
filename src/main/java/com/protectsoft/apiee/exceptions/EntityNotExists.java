package com.protectsoft.apiee.exceptions;

import com.protectsoft.apiee.exceptions.model.ErrorMessage;

/**
 *
 */
public class EntityNotExists extends RuntimeException implements IException {
    
    private ErrorMessage error;
    
    public EntityNotExists() {
        super("");
    }

    public EntityNotExists(Long id, String entitySimpleName) {
        this();
        this.error = new ErrorMessage(id + ":" +entitySimpleName);
    }

    @Override
    public ErrorMessage getError() {
        return error;
    }
    
    public void setError(ErrorMessage error) {
        this.error = error;
    }
    
}
