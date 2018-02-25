package com.protectsoft.apiee.base.boundary;

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
        new Api<BaseEntity>(BaseEntityAUTO.class) {};
    }
    
    @Test(expected = RuntimeException.class)
    public void testApiFacadeEntityConstraint() {
        new Api<BaseEntity>(String.class) {};
    }
    
    @Test
    public void testApiFacadeEntityConstraint2() {
        new Api<BaseEntity>(BaseEntitySequence.class) {};
    }
    
    @Test
    public void testApiFacadeEntityConstraint3() {
        new Api<BaseEntity>(MockEntity.class) {};
    }
    
    
    
    
    private class MockEntity extends BaseEntitySequence {
    }
}
