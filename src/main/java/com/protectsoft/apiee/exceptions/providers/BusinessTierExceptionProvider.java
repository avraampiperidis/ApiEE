package com.protectsoft.apiee.exceptions.providers;

import com.protectsoft.apiee.exceptions.BusinessTierException;
import com.protectsoft.apiee.exceptions.model.ResponseExceptionBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abraham Piperidis
 */
@Provider
public class BusinessTierExceptionProvider implements ExceptionMapper<BusinessTierException> {

    @Override
    public Response toResponse(BusinessTierException ex) {
        return ResponseExceptionBuilder.make(ex);
    }
    
}
