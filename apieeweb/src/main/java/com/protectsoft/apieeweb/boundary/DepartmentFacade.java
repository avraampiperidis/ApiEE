/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.boundary;

import com.protectsoft.apiee.core.Api;
import com.protectsoft.apiee.masterdetail.ManyToManyFunction;
import com.protectsoft.apiee.masterdetail.MoveOption;
import com.protectsoft.apiee.masterdetail.OneToManyFunction;
import com.protectsoft.apiee.masterdetail.OneToOneFunction;
import com.protectsoft.apieeweb.entity.Department;
import com.protectsoft.apieeweb.entity.DepartmentInfo;
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
    public DepartmentFacade(DepartmentFacade childService,EmployeeFacade empService,DepartmentInfoFacade infoService) {
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
        super.addChildDetail(Department.class, Employee.class,new ManyToManyFunction<Department,Employee>() {
                @Override
                public List<Employee> getDetails(Department master) {
                    return master.getEmployees();
                }
                
                @Override
                public void addMaster(Department master, Employee detail) {
                    detail.getDepartments().add(master);
                }
        }, empService, MoveOption.ORPHANS_ALLOWED);
        
        //One To One
        //one Department has One Departnemt Information
        super.addChildDetail(Department.class,DepartmentInfo.class,new OneToOneFunction<Department,DepartmentInfo>(){
            @Override
            public DepartmentInfo getDetail(Department master) {
                return master.getDepartmentInfo();
            }
            
            @Override
            public void setDetail(Department master, DepartmentInfo detail){
                master.setDepartmentInfo(detail);
            }
            
            @Override
            public void setMaster(Department master, DepartmentInfo detail) {
                detail.setDepartment(master);
            }
        }, infoService, MoveOption.ORPHANS_NOT_ALLOWED);
        
    }

}
