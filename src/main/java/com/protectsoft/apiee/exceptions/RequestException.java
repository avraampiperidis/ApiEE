
package com.protectsoft.apiee.exceptions;

import com.protectsoft.apiee.exceptions.model.ErrorMessage;
import javax.ws.rs.BadRequestException;

/**
 *
 */
public class RequestException extends BadRequestException implements IException {
    
    private ErrorMessage error;
    
    public RequestException() {
        super("");
    }
    
    public RequestException(String msg) {
        this();
        this.error = new ErrorMessage(msg);
    }
    
    public RequestException(int status,int code,String msg) {
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
