/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.entity;

import com.protectsoft.apiee.core.annotations.TransformBean;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Abraham Piperidis
 */
@Embeddable
public class EmbeddableDept {
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    private String description;
    
    @TransformBean
    @ManyToOne(fetch = FetchType.LAZY)
    private Department parent;
    
    public EmbeddableDept() {}

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the parent
     */
    public Department getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Department parent) {
        this.parent = parent;
    }
    
}
