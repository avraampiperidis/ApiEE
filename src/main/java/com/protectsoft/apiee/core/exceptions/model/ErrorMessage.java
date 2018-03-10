package com.protectsoft.apiee.core.exceptions.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abraham Piperidis
 */
@XmlRootElement
public class ErrorMessage implements Serializable {
    
    private int status;
    private int code;
    private String message;
    private List<ErrorPairMessage> pairs;
    
    public ErrorMessage() {
        this.message = "";
    }
    
    public ErrorMessage(String msg) {
        this.message = msg;
    }
    
    public ErrorMessage(int status,int code) {
        this();
        this.status = status;
        this.code = code;
    }
    
    public ErrorMessage(int status,int code,String msg) {
        this(msg);
        this.status = status;
        this.code = code;
    }
    
    

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the pairs
     */
    public List<ErrorPairMessage> getPairs() {
        return pairs;
    }

    /**
     * @param pairs the pairs to set
     */
    public void setPairs(List<ErrorPairMessage> pairs) {
        this.pairs = pairs;
    }
    
}
