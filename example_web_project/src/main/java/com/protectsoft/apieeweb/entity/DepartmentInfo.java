/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.entity;

import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import com.protectsoft.apiee.core.annotations.TransformBean;
import com.protectsoft.apiee.core.transformation.Transform;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Abraham Piperidis
 */
@XmlRootElement
@Entity
@Table(name = "DEPARTMENT_INFO")
public class DepartmentInfo extends BaseEntityAUTO implements Transform {

    @NotNull
    @Column(name = "DESCRIPTION",length = 45)
    private String description;
    
    @OneToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @TransformBean
    private Department department;
    
    public DepartmentInfo(){
    }

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
     * @return the department
     */
    @XmlTransient
    public Department getDepartment() {
        return department;
    }
    
    @XmlElement(name = "department")
    public Long getDepartmentId() {
        return department.getId();
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
}
