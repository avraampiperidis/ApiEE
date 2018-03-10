package com.protectsoft.apiee.core.exceptions;

import com.protectsoft.apiee.core.exceptions.model.ErrorMessage;
import java.sql.SQLException;

/**
 *
 * @author Abraham Piperidis
 */
public class SqlException extends SQLException implements IException {
    
    private ErrorMessage error;
    
    public SqlException() {
        super("");
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
