package com.protectsoft.apiee.base.core;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import com.protectsoft.apiee.core.masterdetail.MasterDetailFunction;
import com.protectsoft.apiee.core.masterdetail.ManyToManyFunction;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.protectsoft.apiee.core.masterdetail.OneToOneFunction;
import com.protectsoft.apiee.core.masterdetail.OneToManyFunction;
import java.util.List;

/**
 *
 * @author piper
 */
public class TestContext {
    
    @Test(expected = UnsupportedOperationException.class)
    public void testContextInstance() {
        new Context<BaseEntity>(BaseEntity.class) {
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
            context.addChildDetail(null,null, new OneToOneFunction(){
                @Override
                public BaseEntity getDetail(BaseEntity master) {
                    return null;
                }

                @Override
                public void setMaster(BaseEntity master, BaseEntity detail) {
                }
            }, null, MoveOption.ORPHANS_ALLOWED);
        }catch(RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        ex = null;
        try {
            context.addChildDetail(null,null, new OneToManyFunction() {
                @Override
                public List getDetails(BaseEntity master) {
                    return null;
                }

                @Override
                public void setMaster(BaseEntity master, BaseEntity detail) {
                }
            }, null, MoveOption.ORPHANS_ALLOWED);
        }catch(RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        ex = null;
        try {
            context.addChildDetail(null,null,new ManyToManyFunction() {
                @Override
                public List getDetails(BaseEntity master) {
                    return null;
                }

                @Override
                public void addDetail(BaseEntity master, BaseEntity detail) {
                }

                @Override
                public void addMaster(BaseEntity master, BaseEntity detail) {
                }
            },null , MoveOption.ORPHANS_ALLOWED);
        }catch(RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        throw ex;
    }
    
   
    
    private <T extends BaseEntity> Context<T> getContext() {
        return (Context<T>) new Context<BaseEntity>(BaseEntity.class) {
            @Override
            public void setParent() {
            }

            @Override
            public Api<BaseEntity> getService() {
                return null;
            }
        };
    }
}

