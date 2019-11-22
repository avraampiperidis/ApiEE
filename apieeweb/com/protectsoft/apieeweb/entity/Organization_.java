package com.protectsoft.apieeweb.entity;

import com.protectsoft.apieeweb.entity.Department;
import com.protectsoft.apieeweb.entity.Organization;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-22T14:32:33")
@StaticMetamodel(Organization.class)
public class Organization_ { 

    public static volatile SingularAttribute<Organization, Organization> parent;
    public static volatile SingularAttribute<Organization, String> description;
    public static volatile SingularAttribute<Organization, String> usrcode;
    public static volatile ListAttribute<Organization, Department> departments;
    public static volatile ListAttribute<Organization, Organization> childs;

}