/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.endpoint;

import com.protectsoft.apiee.base.endpoint.jaxrs.ApiResource;
import com.protectsoft.apiee.base.endpoint.jaxrs.ApiSubResource;
import com.protectsoft.apieeweb.boundary.OrganizationFacade;
import com.protectsoft.apieeweb.entity.Department;
import com.protectsoft.apieeweb.entity.Organization;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author piper
 */
@Path("organizations")
public class OrganizationResource extends ApiResource<Organization> {
    
    
    @Inject
    public OrganizationResource(OrganizationFacade service) {
        super(service);
    }
    
    
    
    @Path("{id}/departments")
    public ApiSubResource subResource(@PathParam("id") Long id) {
        return new ApiSubResource<Organization,Department>(id,getService(),Department.class) {};
    }

   
    
}
