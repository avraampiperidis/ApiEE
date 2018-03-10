package com.protectsoft.apiee.core.exceptions.providers;

import com.protectsoft.apiee.core.exceptions.SqlException;
import com.protectsoft.apiee.core.exceptions.model.ResponseExceptionBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abraham Piperidis
 */
@Provider
public class SqlExceptionProvider implements ExceptionMapper<SqlException> {

    @Override
    public Response toResponse(SqlException ex) {
        return ResponseExceptionBuilder.make(ex);
    }
    
}
