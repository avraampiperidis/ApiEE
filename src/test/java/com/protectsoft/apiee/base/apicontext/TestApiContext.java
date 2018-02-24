package com.protectsoft.apiee.base.apicontext;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.boundary.Context;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import com.protectsoft.apiee.base.entities.BaseEntitySequence;
import org.junit.Test;

/**
 *
 * @author piper
 */
public class TestApiContext {
    
    
    @Test
    public void testApiFacadeInstance() {
        Context<BaseEntity> api = new Api<BaseEntity>(BaseEntityAUTO.class) {};
    }
    
    @Test(expected = RuntimeException.class)
    public void testApiFacadeEntityConstraint() {
        Context<BaseEntity> api = new Api<BaseEntity>(String.class) {};
    }
    
    @Test
    public void testApiFacadeEntityConstraint2() {
        Context<BaseEntity> api = new Api<BaseEntity>(BaseEntitySequence.class) {};
    }
    
    @Test
    public void testApiFacadeEntityConstraint3() {
        Context<BaseEntity> api = new Api<BaseEntity>(MockEntity.class) {};
    }
    
    
    
    
    private class MockEntity extends BaseEntitySequence {
    }
}
