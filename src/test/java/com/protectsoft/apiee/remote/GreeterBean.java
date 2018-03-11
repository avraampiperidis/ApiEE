package com.protectsoft.apiee.remote;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Abraham Piperidis
 */
@Stateless(name = "GreeterBean")
public class GreeterBean implements Serializable , Inter {
    
    public GreeterBean() {}
    
    
    public String getGreet() {
        return "Hello";
    }

    @Override
    public String getMsg() {
        return this.getClass().getSimpleName();
    }
}
