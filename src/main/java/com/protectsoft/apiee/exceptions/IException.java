package com.protectsoft.apiee.exceptions;

import com.protectsoft.apiee.exceptions.model.ErrorMessage;

/**
 *
 * @author Abraham Piperidis
 */
public  interface IException {
    ErrorMessage getError();
}
