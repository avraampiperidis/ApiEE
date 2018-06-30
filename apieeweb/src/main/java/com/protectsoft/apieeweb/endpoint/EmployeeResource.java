/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.endpoint;

import com.protectsoft.apiee.base.endpoint.jaxrs.ApiResource;
import com.protectsoft.apieeweb.boundary.EmployeeFacade;
import com.protectsoft.apieeweb.entity.Employee;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author Abraham Piperidis
 */
@Path("employees")
public class EmployeeResource extends ApiResource<Employee> {
    
   @Inject
   public EmployeeResource(EmployeeFacade service){
       super(service);
   }
}
