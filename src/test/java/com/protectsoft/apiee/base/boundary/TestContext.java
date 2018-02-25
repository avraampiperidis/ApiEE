package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author piper
 */
public class TestContext {
    
    @Test(expected = UnsupportedOperationException.class)
    public void testContextInstance() {
        new Context<BaseEntity>() {
            @Override
            public void setParent() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Api<BaseEntity> getService() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
           
        };
    }
    
    
    @Test
    public void testContext() {
        Context<BaseEntity> context = getContext();
        assertTrue(context.getChilds().isEmpty());
        assertTrue(context.getParent() == null);
    }
    
    
    
    private <T extends BaseEntity> Context<T> getContext() {
        return new Context<T>() {
            @Override
            public void setParent() {
            }

            @Override
            public Api<T> getService() {
                return null;
            }
        };
    }
}
