package com.protectsoft.apiee.core.transformation;

import java.lang.reflect.Method;

/**
 *
 * @author Abraham Piperidis
 */
public class TransformRelation {
    
    private Transform instance;
    private Class<?> type;
    private Method method;
    private boolean isEmbedded;
    
    public TransformRelation() {
    }
    
    public TransformRelation(Transform instance,Class<?> type,Method m,boolean isEmb) {
        this.instance = instance;
        this.type = type;
        this.method = m;
        this.isEmbedded = isEmb;
    }

    /**
     * @return the instance
     */
    public Transform getInstance() {
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(Transform instance) {
        this.instance = instance;
    }

    /**
     * @return the type
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Class<?> type) {
        this.type = type;
    }

    /**
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * @return the isEmbedded
     */
    public boolean isIsEmbedded() {
        return isEmbedded;
    }

    /**
     * @param isEmbedded the isEmbedded to set
     */
    public void setIsEmbedded(boolean isEmbedded) {
        this.isEmbedded = isEmbedded;
    }
}
