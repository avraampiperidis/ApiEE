package com.protectsoft.apiee.base.interfaces;

import com.protectsoft.apiee.base.entities.BaseEntity;

/**
 *
 * @param <DB>
 * @param <DAO>
 */
public interface IValidation<DB extends BaseEntity,DAO extends BaseEntity> {
    
   void validateUpdate(DB db,DAO dao);
    
   void validate(DAO dao);
}
