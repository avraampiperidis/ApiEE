package com.protectsoft.apiee.core.exceptions.providers;

import com.protectsoft.apiee.core.exceptions.SystemException;
import com.protectsoft.apiee.core.exceptions.model.ResponseExceptionBuilder;
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
