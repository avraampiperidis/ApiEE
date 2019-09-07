package com.protectsoft.apiee.exceptions.providers;

import com.protectsoft.apiee.exceptions.RequestException;
import com.protectsoft.apiee.exceptions.model.ResponseExceptionBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abraham Piperidis
 */
@Provider
public class RequestExceptionProvider implements ExceptionMapper<RequestException> {

    @Override
    public Response toResponse(RequestException ex) {
        return ResponseExceptionBuilder.make(ex);
    }
    
}
