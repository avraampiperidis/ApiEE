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
import com.protectsoft.apieeweb.entity.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author piper
 */
@Stateless
public class DepartmentFacade extends Api<Department> {

    public DepartmentFacade() {
        super(Department.class);
    }
    
    @Inject
    public DepartmentFacade(EmployeeFacade empService) {
        this();
        super.addChildDetail(Department.class,Department.class,new MasterDetailFunction<Department,Department>() {
                @Override
                public List<Department> getDetails(Department master) {
                    return master.getChilds();
                }

                @Override
                public void setMaster(Department master,Department child) {
                    child.setParent(master);
                }
            },this, MoveOption.ORPHANS_ALLOWED);
        
        super.addChildDetail(Department.class,Employee.class, new MasterDetailFunction<Department,Employee>(){
            @Override
            public List<Employee> getDetails(Department parent) {
                return parent.getEmployees();
            }
        }, empService, MoveOption.ORPHANS_ALLOWED);
    }

   
  

  
    
}
