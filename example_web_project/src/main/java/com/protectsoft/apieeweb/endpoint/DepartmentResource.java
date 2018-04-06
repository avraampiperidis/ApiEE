/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.endpoint;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.endpoint.ApiResource;
import com.protectsoft.apiee.base.endpoint.ApiSubResource;
import com.protectsoft.apieeweb.boundary.DepartmentFacade;
import com.protectsoft.apieeweb.entity.Department;
import com.protectsoft.apieeweb.entity.Employee;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
    
    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo ui,Department dept) {
        return super.create(ui, dept);
    }
    
    @Path("{id}/departments")
    public ApiSubResource subResource(@PathParam("id") Long id) {
        return new SubResource(id,getService());
    }
    
    @Path("{id}/employees")
    public ApiSubResource subResource2(@PathParam("id") Long id) {
        System.out.println("subResource2");
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
