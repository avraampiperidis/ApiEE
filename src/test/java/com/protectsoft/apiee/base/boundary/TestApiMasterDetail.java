package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.core.Mock.MockEntityChild;
import com.protectsoft.apiee.core.Mock.MockEntityParent;
import com.protectsoft.apiee.core.Mock.MockFacadeChild;
import com.protectsoft.apiee.core.Mock.MockFacadeParent;
import com.protectsoft.apiee.core.masterdetail.MasterDetailFunction;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Abraham Piperidis
 */
public class TestApiMasterDetail {
    
    private MockFacadeParent parent;
    private MockFacadeChild child;
    private MockFacadeChild child2;
    
    
    @Before
    public void beforeTest() {
        parent = new MockFacadeParent();
        child = new MockFacadeChild();
        child2 = new MockFacadeChild();
        parent.addChildDetail(MockEntityParent.class,MockEntityChild.class, new MasterDetailFunction<MockEntityParent, MockEntityChild>() {
        },child, MoveOption.ORPHANS_ALLOWED);
        parent.addChildDetail(MockEntityParent.class,MockEntityChild.class, new MasterDetailFunction<MockEntityParent, MockEntityChild>() {
        },child2, MoveOption.ONE_TO_ONE);
    }
    
    
    @Test
    public void testAddChildDetails() {
         assertEquals(parent,child.getParent());
         assertEquals(parent,child2.getParent());
    }
    
    @Test
    public void testAddChildDetailPair() {
        parent.getChilds().get(0);
        parent.getChilds().get(0).getMasterDetailHolder();
        parent.getChilds().get(0).getMasterDetailHolder().getChildClass();
        
        assertEquals(MockEntityChild.class,parent.getChilds().get(0).getMasterDetailHolder().getChildClass());
        assertEquals(MockEntityParent.class,parent.getChilds().get(0).getMasterDetailHolder().getMasterClass());
        assertEquals(MockEntityParent.class,parent.getChilds().get(1).getMasterDetailHolder().getMasterClass());
        assertEquals(MockEntityChild.class,parent.getChilds().get(1).getMasterDetailHolder().getChildClass());
        assertEquals(MoveOption.ORPHANS_ALLOWED,parent.getChilds().get(0).getMasterDetailHolder().getMoveOption());
        assertEquals(MoveOption.ONE_TO_ONE,parent.getChilds().get(1).getMasterDetailHolder().getMoveOption());
        assertEquals(child,parent.getChilds().get(0).getApi());
        assertEquals(child2,parent.getChilds().get(1).getApi());
        assertEquals(parent,parent.getChilds().get(1).getApi().getParent());
    }
    
    
    
    
}
