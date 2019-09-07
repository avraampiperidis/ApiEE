package com.protectsoft.apiee.exceptions.providers;

import com.protectsoft.apiee.exceptions.SqlException;
import com.protectsoft.apiee.exceptions.model.ResponseExceptionBuilder;
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
