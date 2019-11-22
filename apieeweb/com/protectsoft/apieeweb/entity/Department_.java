package com.protectsoft.apieeweb.entity;

import com.protectsoft.apieeweb.entity.Department;
import com.protectsoft.apieeweb.entity.DepartmentInfo;
import com.protectsoft.apieeweb.entity.EmbeddableDept;
import com.protectsoft.apieeweb.entity.Employee;
import com.protectsoft.apieeweb.entity.Organization;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-22T14:32:33")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, EmbeddableDept> embDept;
    public static volatile SingularAttribute<Department, Organization> organization;
    public static volatile SingularAttribute<Department, DepartmentInfo> departmentInfo;
    public static volatile ListAttribute<Department, Employee> employees;
    public static volatile ListAttribute<Department, Department> childs;

}