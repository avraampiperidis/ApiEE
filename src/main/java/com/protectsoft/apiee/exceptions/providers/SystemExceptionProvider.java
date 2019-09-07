package com.protectsoft.apiee.exceptions.providers;

import com.protectsoft.apiee.exceptions.SystemException;
import com.protectsoft.apiee.exceptions.model.ResponseExceptionBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abraham Piperidis
 */
@Provider
public class SystemExceptionProvider implements ExceptionMapper<SystemException> {

    @Override
    public Response toResponse(SystemException ex) {
        return ResponseExceptionBuilder.make(ex);
    }
    
}
