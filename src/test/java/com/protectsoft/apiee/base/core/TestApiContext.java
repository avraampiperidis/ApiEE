package com.protectsoft.apiee.base.core;

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
        new Api(BaseEntityAUTO.class) {};
    }
    
    @Test(expected = RuntimeException.class)
    public void testApiFacadeEntityConstraint() {
        new Api(String.class) {};
    }
    
    @Test
    public void testApiFacadeEntityConstraint2() {
        new Api(BaseEntitySequence.class) {};
    }
    
    @Test
    public void testApiFacadeEntityConstraint3() {
        new Api<MockEntity>(MockEntity.class) {};
    }
    
    
    
    
    private class MockEntity extends BaseEntitySequence {
    }
}
