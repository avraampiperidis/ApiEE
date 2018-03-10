package com.protectsoft.apiee.core.exceptions;

import com.protectsoft.apiee.core.exceptions.model.ErrorMessage;

/**
 *
 * @author Abraham Piperidis
 */
public interface IException {
    ErrorMessage getError();
}
