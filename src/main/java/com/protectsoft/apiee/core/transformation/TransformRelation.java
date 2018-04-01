package com.protectsoft.apiee.core.transformation;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 *
 * @author Abraham Piperidis
 */
public class TransformRelation {
    
    private Object instance;
    private Class<?> type;
    private Method method;
    private String fieldName;
    
    public TransformRelation() {
    }
    
    public TransformRelation(Transform instance,Class<?> type,Method m) {
        this.instance = instance;
        this.type = type;
        this.method = m;
    }

    /**
     * @return the instance
     */
    public Object getInstance() {
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(Object instance) {
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
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof TransformRelation)) {
            return false;
        }
        TransformRelation o2 = (TransformRelation)o;
        if(!o2.getFieldName().equals(this.fieldName)) {
            return false;
        }
        if(!o2.getInstance().equals(this)) {
            return false;
        }
        
        return o2.getType() == this.getClass();
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.instance);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.fieldName);
        return hash;
    }
    
    @Override
    public String toString() {
        return "{instance:"+instance.getClass()+",type:"+type+",method:"+method+",fieldName:"+fieldName;
    }
    
}
