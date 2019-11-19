/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.entity;

import com.protectsoft.apiee.annotations.TransformBean;
import com.protectsoft.apiee.core.transformation.Transform;
import com.protectsoft.apiee.entities.BaseEntityAUTO;
import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author piper
 */
@Entity
@Table(name = "DEPARTMENTS")
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
public class Department extends BaseEntityAUTO implements Transform   {
    
    @Embedded
    @AttributeOverrides({
    @AttributeOverride(name="description", column=@Column(name="DESCRIPTION")),
    })
    @AssociationOverrides({
      @AssociationOverride(name = "parent",
                  joinColumns = @JoinColumn(name = "PARENT_ID",referencedColumnName = "ID"))
    })
    private EmbeddableDept embDept;

    
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Department> childs;
    
    @ManyToMany
    @JoinTable(name = "DEP_EMPS",
            joinColumns=@JoinColumn(name="DEPARTMENT_ID",referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="EMPLOYEE_ID",referencedColumnName="ID"))
    private List<Employee> employees;
    
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @TransformBean
    private Organization organization;
    
    @OneToOne(mappedBy = "department")
    private DepartmentInfo departmentInfo;
    
    
    public Department() {}


    /**
     * @return the employees
     */
    @XmlTransient
    @JsonbTransient
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
    @JsonbTransient
    public Organization getOrganization() {
        return organization;
    }
    
    @XmlElement(name = "organization")
    @JsonbProperty("organization")
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
    @JsonbTransient
    public List<Department> getChilds() {
        return childs;
    }

    /**
     * @param childs the childs to set
     */
    public void setChilds(List<Department> childs) {
        this.childs = childs;
    }

    /**
     * @return the embDept
     */
    public EmbeddableDept getEmbDept() {
        return embDept;
    }

    /**
     * @param embDept the embDept to set
     */
    public void setEmbDept(EmbeddableDept embDept) {
        this.embDept = embDept;
    }

    /**
     * @return the departmentInfo
     */
    public DepartmentInfo getDepartmentInfo() {
        return departmentInfo;
    }

    /**
     * @param departmentInfo the departmentInfo to set
     */
    public void setDepartmentInfo(DepartmentInfo departmentInfo) {
        this.departmentInfo = departmentInfo;
    }
    
}
