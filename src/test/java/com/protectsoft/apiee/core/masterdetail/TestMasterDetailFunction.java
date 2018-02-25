package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.core.Mock;
import com.protectsoft.apiee.core.Mock.MockEntityChild;
import com.protectsoft.apiee.core.Mock.MockEntityChild2;
import com.protectsoft.apiee.core.Mock.MockEntityParent;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Abraham Piperidis
 */
public class TestMasterDetailFunction {
    
    private Mock.MockFacadeParent parent;
    private Mock.MockFacadeChild child;
    private Mock.MockFacadeChild child2;
    private Mock.MockFacadeChild2 child3;
    
    
    @Before
    public void beforeTest() {
        parent = new Mock.MockFacadeParent();
        child = new Mock.MockFacadeChild();
        child2 = new Mock.MockFacadeChild();
        child3 = new Mock.MockFacadeChild2();       
        parent.addChildDetail(Mock.MockEntityParent.class,Mock.MockEntityChild.class, new MasterDetailFunction<Mock.MockEntityParent, Mock.MockEntityChild>() {
            @Override
            public List<MockEntityChild> getDetails(MockEntityParent master){
                return master.getChilds();
            };
            @Override
            public MockEntityChild getDetail(MockEntityParent master){
                return null;
            };
            @Override
            public void setMaster(MockEntityParent master,MockEntityChild detail){
                detail.setParent(master);
            };
        },child, MoveOption.ORPHANS_ALLOWED);
        parent.addChildDetail(Mock.MockEntityParent.class,Mock.MockEntityChild.class, new MasterDetailFunction<Mock.MockEntityParent, Mock.MockEntityChild>() {
            @Override
            public List<MockEntityChild> getDetails(MockEntityParent master){
                return master.getChilds();
            };
            @Override
            public MockEntityChild getDetail(MockEntityParent master){
                return null;
            };
            @Override
            public void setMaster(MockEntityParent master,MockEntityChild detail){
                detail.setParent(master);
            };
        },child2, MoveOption.ONE_TO_ONE);
        parent.addChildDetail(Mock.MockEntityParent.class,Mock.MockEntityChild2.class, new MasterDetailFunction<Mock.MockEntityParent, Mock.MockEntityChild2>() {
            @Override
            public List<MockEntityChild2> getDetails(MockEntityParent master){
                return master.getChilds2();
            };
            @Override
            public MockEntityChild2 getDetail(MockEntityParent master){
                return null;
            };
            @Override
            public void setMaster(MockEntityParent master,MockEntityChild2 detail){
                detail.setParent(master);
            };
        },child3, MoveOption.ONE_TO_ONE);
    }
    
    
    
    @Test
    public void testGetDetails() {
        MockEntityParent parentMock = mock(MockEntityParent.class);
        MockEntityChild mock1 = mock(MockEntityChild.class);
        MockEntityChild mock2 = mock(MockEntityChild.class);
        List<MockEntityChild> mocks = Arrays.asList(mock1,mock2);
        when(parentMock.getChilds()).thenReturn(mocks);
        assertEquals(2,parent.getChilds().get(0).getMasterDetailHolder().getFunction().getDetails(parentMock).size());
        assertEquals(mock1,parent.getChilds().get(0).getMasterDetailHolder().getFunction().getDetails(parentMock).get(0));
        assertEquals(mock2,parent.getChilds().get(0).getMasterDetailHolder().getFunction().getDetails(parentMock).get(1));
    }
    
    
    @Test
    public void testGetDetail() {
        //not implemented yet one to one
    }
    
    
    @Test
    public void testSetMaster() {
        MockEntityParent parentMock = new MockEntityParent();//in this case mocks returns null after set/get mock(MockEntityParent.class);
        MockEntityChild2 mock1 = new MockEntityChild2();//mock(MockEntityChild2.class);
        assertTrue(parentMock.getChilds2().isEmpty());
        assertTrue(mock1.getParent() == null);
        parent.getChilds().get(2).getMasterDetailHolder().getFunction().setMaster(parentMock, mock1);
        assertEquals(parentMock,mock1.getParent());
        assertTrue(parentMock.getChilds2().isEmpty());
    }
    
}
