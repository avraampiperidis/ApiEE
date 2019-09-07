package com.protectsoft.apiee.exceptions.providers;

import com.protectsoft.apiee.exceptions.BusinessTierException;
import com.protectsoft.apiee.exceptions.model.ResponseExceptionBuilder;
import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abraham Piperidis
 */
@Provider
public class EJBExceptionProvider implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException exception) {
        return ResponseExceptionBuilder.make(new BusinessTierException(exception.getMessage(),exception));
    }
    
}
