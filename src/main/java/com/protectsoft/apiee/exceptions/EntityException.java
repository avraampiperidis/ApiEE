package com.protectsoft.apiee.exceptions;

import com.protectsoft.apiee.exceptions.model.ErrorMessage;

/**
 *
 */
public class EntityException extends RuntimeException implements IException {
    
    private ErrorMessage error;
    
    public EntityException() {
        super("");
    }

    public EntityException(int status, int code, String msg) {
        this();
        this.error = new ErrorMessage(status, code, msg);
    }

    /**
     * @return the error
     */
    @Override
    public ErrorMessage getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(ErrorMessage error) {
        this.error = error;
    }
    
}
