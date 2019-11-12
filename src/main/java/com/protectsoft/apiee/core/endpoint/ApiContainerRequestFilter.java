
package com.protectsoft.apiee.core.endpoint;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @todo Replace this filter with custom user modular one.
 */
@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class ApiContainerRequestFilter implements ContainerRequestFilter {
    
    /**
     * Default behavior. Allow all OPTION requests
     */
    @Override
    public void filter(ContainerRequestContext requestContext) {
        if(requestContext.getRequest().getMethod().equals("OPTIONS")) {
            requestContext.abortWith(Response.status(Response.Status.OK).build());
        } 
    }
    
}
