package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Abraham Piperidis
 */
public class Validator {
    
    <T extends BaseEntity> void validate(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = (javax.validation.Validator) factory.getValidator();
        Set<ConstraintViolation<T>> cv = validator.validate(entity);
        if (!cv.isEmpty()) { 
           throw new ConstraintViolationException(cv);
        }

        if(!entity.isValid()) {
            throw new ConstraintViolationException("Entity validation failed", null);
        }
    }
    
    
    <DB extends BaseEntity,DAO extends BaseEntity> void validateUpdate(DB db,DAO entity) {
    }
    
}
