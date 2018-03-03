/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.boundary;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.core.masterdetail.MasterDetailFunction;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import com.protectsoft.apieeweb.entity.Department;
import com.protectsoft.apieeweb.entity.Organization;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author piper
 */
@Stateless
public class OrganizationFacade extends Api<Organization> {
    
    public OrganizationFacade() {
        super(Organization.class);
    }
    
    @Inject
    public OrganizationFacade(DepartmentFacade departmentService) {
        this();
        
        super.addChildDetail(Organization.class,Department.class,new MasterDetailFunction<Organization,Department>(){
            @Override
            public List<Department> getDetails(Organization parent) {
                return parent.getDepartments();
            }
        }, departmentService, MoveOption.ORPHANS_ALLOWED);
    }
    
}
