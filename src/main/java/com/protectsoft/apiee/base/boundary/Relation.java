package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;

/**
 *
 * @author Abraham Piperidis
 * @param <T>
 */
public class Relation<T extends BaseEntity> {
    
    private final Long parentId;
    private final Class<T> childClass;
    
    public Relation(Long id,Class<T> childClass) {
        this.parentId = id;
        this.childClass = childClass;
    }


    /**
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @return the childClass
     */
    public Class getChildClass() {
        return childClass;
    }
    
}
