package com.protectsoft.apiee.exceptions;

import com.protectsoft.apiee.exceptions.model.ErrorMessage;
import javax.ejb.ApplicationException;

/**
 *
 * @author Abraham Piperidis
 */
@ApplicationException(rollback = true)
public class SystemException extends Exception implements IException {
    
    private ErrorMessage error;
    
    public SystemException() {
    }
    
    public SystemException(int status,int code,String msg) {
        super(msg);
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
