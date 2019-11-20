package com.protectsoft.apiee.core;

import com.protectsoft.apiee.entities.BaseEntity;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Abraham Piperidis
 */
public class CustomValidator {
    
    static <T extends BaseEntity>  void validate(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = (Validator) factory.getValidator();
        Set<?> cv = validator.validate(entity);
        if (!cv.isEmpty()) { 
            ConstraintViolationException cve = new ConstraintViolationException("", (Set<? extends ConstraintViolation<?>>) cv);
           //throw new ConstraintViolationException(cv);
        }

        if(!entity.isValid()) {
            throw new ConstraintViolationException("Entity validation failed", null);
        }
    }
    
    
    static <DB extends BaseEntity,DAO extends BaseEntity> void validateUpdate(DB db,DAO entity) {
    }
    
}
