package com.protectsoft.apiee.remote;

import javax.ejb.Stateless;

/**
 *
 * @author Abraham Piperidis
 */
@Stateless(name = "MockBean")
public class MockBean implements Inter {
    
    public MockBean() {}
    
    public String getMsg() {
        return this.getClass().getSimpleName();
    }
    
}
