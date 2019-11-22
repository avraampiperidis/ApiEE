/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.endpoint;

import com.protectsoft.apiee.AAA.IApiOneToOneSubResource;
import com.protectsoft.apiee.core.Api;
import com.protectsoft.apiee.endpoint.jaxrs.ApiOneToOneSubResource;
import com.protectsoft.apiee.endpoint.jaxrs.ApiSubResource;
import com.protectsoft.apiee.AAA.IApiResource;
import com.protectsoft.apiee.AAA.IApiSubResource;
import com.protectsoft.apieeweb.AAA.DepartmentAnno;
import com.protectsoft.apieeweb.boundary.IDepartmentService;
import com.protectsoft.apieeweb.entity.Department;
import com.protectsoft.apieeweb.entity.DepartmentInfo;
import com.protectsoft.apieeweb.entity.Employee;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
/**
 *
 * @author piper
 */
@Path("departments")
public class DepartmentResource  implements IApiResource<Department> {
    
    private IDepartmentService<Department> service;
    
    public DepartmentResource(){
    }
    
    @Inject
    public DepartmentResource(@DepartmentAnno IDepartmentService<Department> service) {
        //super(service); 
        this.service = service;
    }
    
    @Override
    public void meth() {
        System.out.println(this.getClass().getSimpleName()+",meth()");
    }
    
    @Path("{id}/departments")
    public IApiSubResource subResource(@PathParam("id") Long id) {
        //return new SubResource(id,getService());
        //return new IApiSubResource<Department,Department>(){};
        return new SubResource();
    }
    
    @Path("{id}/employees")
    public IApiSubResource subResource2(@PathParam("id") Long id) {
        //return new SubResource2(id,getService());
        return new SubResource2();
    }
    
    @Path("{id}/departmentInfo")
    public IApiOneToOneSubResource subResource3(@PathParam("id") Long id) {
        //return new SubResource3(id,getService());
        return new OneToOneSubResource();
    }
    
    
    public class OneToOneSubResource implements IApiOneToOneSubResource<Department,DepartmentInfo> {
//        public SubResource3(Long id,Api<Department> service) {
//           super(id,service,DepartmentInfo.class);
//        }
    }
    
    public class SubResource2 implements IApiSubResource<Department,Employee> {
//        public SubResource2(Long id,Api<Department> service) {
//            super(id,service,Employee.class);
//        }
    }
    
    public class SubResource implements IApiSubResource<Department,Department> {
//        public SubResource(Long id,Api<Department> service) {
//            super(id,service,Department.class);
//        }
        @Path("{id}/departments")
        public IApiSubResource subResource(@PathParam("id") Long id) {
            //return new SubResource(id,getService());
            return new SubResource();
        }
    }

}
