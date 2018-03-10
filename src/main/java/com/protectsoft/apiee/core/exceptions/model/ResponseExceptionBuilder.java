package com.protectsoft.apiee.core.exceptions.model;

import com.protectsoft.apiee.core.exceptions.IException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Abraham Piperidis
 */
public class ResponseExceptionBuilder {
    
    public static Response make(IException ex) {
        return Response.status(ex.getError().getStatus())
                .entity(ex.getError())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
