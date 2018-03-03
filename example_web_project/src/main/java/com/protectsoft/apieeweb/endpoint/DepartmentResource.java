/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.endpoint;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.endpoint.ApiResource;
import com.protectsoft.apiee.base.endpoint.ApiSubResource;
import com.protectsoft.apieeweb.boundary.DepartmentFacade;
import com.protectsoft.apieeweb.entity.Department;
import com.protectsoft.apieeweb.entity.Employee;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
/**
 *
 * @author piper
 */
@Path("departments")
public class DepartmentResource extends ApiResource<Department> {

 
    @Inject
    public DepartmentResource(DepartmentFacade service) {
        super(service); 
    }
    
    @Path("{id}/departments")
    public ApiSubResource subResource(@PathParam("id") Long id) {
        return new SubResource(id,getService());
    }
    
    @Path("{id}/employess")
    public ApiSubResource subResource2(@PathParam("id") Long id) {
        return new SubResource2(id,getService());
    }

    
    
    public class SubResource2 extends ApiSubResource<Department,Employee> {
        public SubResource2(Long id,Api<Department> service) {
            super(id,service,Employee.class);
        }
    }
    
    public class SubResource extends ApiSubResource<Department,Department> {
        
        public SubResource(Long id,Api<Department> service) {
            super(id,service,Department.class);
        }
        
        @Path("{id}/departments")
        public ApiSubResource subResource(@PathParam("id") Long id) {
            return new SubResource(id,getService());
        }
    }

  
    

    
}
