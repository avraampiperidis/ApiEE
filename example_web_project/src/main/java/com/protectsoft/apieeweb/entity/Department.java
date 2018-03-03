/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.entity;

import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "DEPARTMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
})
public class Department extends BaseEntityAUTO  {
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "DESCRIPTION", nullable = false, length = 85)
    private String description;
    
    @JoinColumn(name = "PARENT_ID",referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department parent;
    
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Department> childs;
    
    @ManyToMany
    @JoinTable(name = "DEP_EMPS",
            joinColumns=@JoinColumn(name="DEPARTMENT_ID",referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="EMPLOYEE_ID",referencedColumnName="ID"))
    private List<Employee> employees;
    
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;
    
    public Department() {}

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
    public Department getParent() {
        return parent;
    }
    
    @XmlElement(name = "parent")
    public Long getParentId() {
        if(parent != null) {
            return parent.getId();
        }
        return null;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Department parent) {
        this.parent = parent;
    }

    /**
     * @return the employees
     */
    @XmlTransient
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * @param employees the employees to set
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * @return the organization
     */
    @XmlTransient
    public Organization getOrganization() {
        return organization;
    }
    
    @XmlElement(name = "organization")
    public Long getOrganizationId() {
        return organization.getId();
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * @return the childs
     */
    @XmlTransient
    public List<Department> getChilds() {
        return childs;
    }

    /**
     * @param childs the childs to set
     */
    public void setChilds(List<Department> childs) {
        this.childs = childs;
    }
    
}
