/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.entity;

import com.protectsoft.apiee.core.transformation.Transform;
import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author piper
 */
@Entity
@Table(name = "ORGANIZATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o")
})
public class Organization extends BaseEntityAUTO   {
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "USRCODE", nullable = false, length = 10)
    private String usrcode;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DESCRIPTION", nullable = false, length = 60)
    private String description;
    
    @JoinColumn(name = "PARENT_ID",referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization parent;
    
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Organization> childs;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "organization")
    private List<Department> departments;
    
    public Organization() {}

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
    @XmlTransient
    public Organization getParent() {
        return parent;
    }
    
    @XmlElement(name = "parent")
    public Long getparentId() {
        if(parent != null) {
            return parent.getId();
        }
        return null;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Organization parent) {
        this.parent = parent;
    }

    /**
     * @return the departments
     */
    @XmlTransient
    public List<Department> getDepartments() {
        return departments;
    }

    /**
     * @param departments the departments to set
     */
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    /**
     * @return the usrcode
     */
    public String getUsrcode() {
        return usrcode;
    }

    /**
     * @param usrcode the usrcode to set
     */
    public void setUsrcode(String usrcode) {
        this.usrcode = usrcode;
    }

    /**
     * @return the childs
     */
    @XmlTransient
    public List<Organization> getChilds() {
        return childs;
    }

    /**
     * @param childs the childs to set
     */
    public void setChilds(List<Organization> childs) {
        this.childs = childs;
    }
    
}
