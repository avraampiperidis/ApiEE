package com.protectsoft.apiee.base.boundary;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Avraam Piperidis
 */
public abstract class Context {

    private final List<ApiFacade<?>> childs;
    private ApiFacade<?> parent;
    
    Context() {
        this.childs = new ArrayList<>();
    }
    
    
    protected abstract ApiFacade<?> getService();

    /**
     * @return the child services
     */
    public List<ApiFacade<?>> getChilds() {
        return childs;
    }

    /**
     * @return the parent service
     */
    public ApiFacade<?> getParent() {
        return parent;
    }
    
}
