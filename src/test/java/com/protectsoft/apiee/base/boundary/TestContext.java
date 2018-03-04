package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import com.protectsoft.apiee.core.masterdetail.DetailFunction;
import com.protectsoft.apiee.core.masterdetail.DetailsFunction;
import com.protectsoft.apiee.core.masterdetail.MasterDetailFunction;
import com.protectsoft.apiee.core.masterdetail.MasterFunction;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import static org.junit.Assert.assertNotNull;
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
        assertTrue(context.getChildDetails().isEmpty());
        assertTrue(context.getParent() == null);
    }
    
    
    @Test(expected = RuntimeException.class)
    public void testMasterDetailConstraintEX() {
        Context<BaseEntityAUTO> context = getContext();
        RuntimeException ex = null;
        try {
            context.addChildDetail(null,null, (DetailFunction<BaseEntityAUTO,BaseEntityAUTO>) (BaseEntityAUTO master) -> null, null, MoveOption.ONE_TO_ONE);
        }catch(RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        ex = null;
        try {
            context.addChildDetail(null,null, (DetailsFunction<BaseEntityAUTO,BaseEntityAUTO>) (BaseEntityAUTO master) -> null, null, MoveOption.ONE_TO_ONE);
        }catch(RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        ex = null;
        try {
            context.addChildDetail(null,null, (MasterFunction<BaseEntityAUTO,BaseEntityAUTO>) (BaseEntityAUTO master, BaseEntityAUTO detail) -> {
            },null , MoveOption.ONE_TO_ONE);
        }catch(RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        throw ex;
    }
    
    
    @Test
    public void testMasterDetailConstraint() {
        Context<BaseEntityAUTO> context = new Api(BaseEntityAUTO.class) {};
        context.addChildDetail(null,null,new MasterDetailFunction<BaseEntityAUTO,BaseEntityAUTO>(){},new Api(BaseEntityAUTO.class) {}, MoveOption.ONE_TO_ONE);
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
