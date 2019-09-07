package com.protectsoft.apiee.exceptions.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abraham Piperidis
 */
@XmlRootElement
public class ErrorPairMessage implements Serializable {
    
    private String type;
    private String message;
    
    public ErrorPairMessage() {}
    
    public ErrorPairMessage(String type,String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
    
}
