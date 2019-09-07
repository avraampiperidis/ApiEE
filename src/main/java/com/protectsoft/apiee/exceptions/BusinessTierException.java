package com.protectsoft.apiee.exceptions;

import com.protectsoft.apiee.exceptions.model.ErrorMessage;
import javax.ejb.EJBException;

/**
 *
 * @author Abraham Piperidis
 */
public class BusinessTierException  extends EJBException implements IException {
    
    private ErrorMessage error;
    
    public BusinessTierException() {
        super("");
    }
    
    public BusinessTierException(String msg) {
        super(msg);
        this.error = new ErrorMessage(msg);
    }
    
    public BusinessTierException(String msg,Exception ex) {
        super(msg,ex);
        this.error = new ErrorMessage(msg);
    }
    
    public BusinessTierException(int status,int code,String msg) {
        super(msg);
        this.error = new ErrorMessage(status, code, msg);
    }
    
    public BusinessTierException(int status,int code,String msg,Exception ex) {
        super(msg,ex);
        this.error = new ErrorMessage(status, code, msg);
    }
    
    public BusinessTierException(int status,int code,Exception ex) {
        super(ex);
        this.error = new ErrorMessage(status, code);
    }
    
    public BusinessTierException(Exception ex) {
        super(ex);
        this.error = new ErrorMessage(500,100);
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
