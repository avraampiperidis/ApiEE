/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.boundary;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.core.masterdetail.ManyToManyFunction;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import com.protectsoft.apiee.core.masterdetail.OneToManyFunction;
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
    public DepartmentFacade(DepartmentFacade childService,EmployeeFacade empService) {
        this();
        //one to many
        //one department has many sub departments
        super.addChildDetail(Department.class,Department.class,new OneToManyFunction<Department,Department>() {
                @Override
                public List<Department> getDetails(Department master) {
                    return master.getChilds();
                }

                @Override
                public void setMaster(Department master,Department child) {
                    child.getEmbDept().setParent(master);
                }
            },childService, MoveOption.ORPHANS_ALLOWED);
        
        //many to many
        //one department has many employes
        //but also one employee can work on many departments
        addChildDetail(Department.class, Employee.class,new ManyToManyFunction<Department,Employee>() {
                @Override
                public List<Employee> getDetails(Department master) {
                    return master.getEmployees();
                }
                
                @Override
                public void addMaster(Department master, Employee detail) {
                    detail.getDepartments().add(master);
                }
            }, empService, MoveOption.ORPHANS_ALLOWED);
        
    }

   
  

  
    
}
