package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.core.MyMock;
import com.protectsoft.apiee.core.MyMock.MockEntityChild;
import com.protectsoft.apiee.core.MyMock.MockEntityChild2;
import com.protectsoft.apiee.core.MyMock.MockEntityOtherChild;
import com.protectsoft.apiee.core.MyMock.MockEntityParent;
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
    
    
    private MyMock.MockFacadeParent parentFacade;
    private MyMock.MockFacadeChild childFacade;
    private MyMock.MockFacadeChild child2Facade;
    private MyMock.MockFacadeChild2 child3Facade;
    private MyMock.MockFacadeOtherChild otherSubFacade;
    
    
    @Before
    public void beforeTest() {
        parentFacade = new MyMock.MockFacadeParent();
        childFacade = new MyMock.MockFacadeChild();
        child2Facade = new MyMock.MockFacadeChild();
        child3Facade = new MyMock.MockFacadeChild2();       
        otherSubFacade = new MyMock.MockFacadeOtherChild();       
        parentFacade.addChildDetail(MyMock.MockEntityParent.class,MyMock.MockEntityChild.class, new MasterDetailFunction<MyMock.MockEntityParent, MyMock.MockEntityChild>() {
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
        },childFacade, MoveOption.ORPHANS_ALLOWED);
        parentFacade.addChildDetail(MyMock.MockEntityParent.class,MyMock.MockEntityChild.class, new MasterDetailFunction<MyMock.MockEntityParent, MyMock.MockEntityChild>() {
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
        },child2Facade, MoveOption.ONE_TO_ONE);
        parentFacade.addChildDetail(MyMock.MockEntityParent.class,MyMock.MockEntityChild2.class, new MasterDetailFunction<MyMock.MockEntityParent, MyMock.MockEntityChild2>() {
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
        },child3Facade, MoveOption.ONE_TO_ONE);
        
        child3Facade.addChildDetail(MyMock.MockEntityChild2.class, MyMock.MockEntityOtherChild.class,new MasterDetailFunction<MyMock.MockEntityChild2, MyMock.MockEntityOtherChild>() {
            @Override
            public List<MyMock.MockEntityOtherChild> getDetails(MockEntityChild2 master) {
                return master.getChilds();
            }

            @Override
            public MockEntityOtherChild getDetail(MockEntityChild2 master) {
                return null;
            }

            @Override
            public void setMaster(MockEntityChild2 master, MyMock.MockEntityOtherChild detail) {
                detail.setParent(master);
            }
        } , otherSubFacade, MoveOption.ORPHANS_NOT_ALLOWED);
    }
    
    
    @Test
    public void testGetChilds() {
        assertEquals(3,parentFacade.getChilds().size());
        assertEquals(childFacade,parentFacade.getChilds().get(0));
        assertEquals(parentFacade,parentFacade.getChilds().get(2).getParent().getParent());
    }
    
    @Test
    public void testGetDetails() {
        MockEntityParent parentMock = mock(MockEntityParent.class);
        MockEntityChild mock1 = mock(MockEntityChild.class);
        MockEntityChild mock2 = mock(MockEntityChild.class);
        List<MockEntityChild> mocks = Arrays.asList(mock1,mock2);
        when(parentMock.getChilds()).thenReturn(mocks);
        assertEquals(2,parentFacade.getChildDetails().get(0).getMasterDetailHolder().getFunction().getDetails(parentMock).size());
        assertEquals(mock1,parentFacade.getChildDetails().get(0).getMasterDetailHolder().getFunction().getDetails(parentMock).get(0));
        assertEquals(mock2,parentFacade.getChildDetails().get(0).getMasterDetailHolder().getFunction().getDetails(parentMock).get(1));
    }
    
    
    @Test
    public void testGetDetailsOther(){
        assertEquals(child3Facade,otherSubFacade.getParent());
        assertEquals(parentFacade,otherSubFacade.getParent().getParent());
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
        parentFacade.getChildDetails().get(2).getMasterDetailHolder().getFunction().setMaster(parentMock, mock1);
        assertEquals(parentMock,mock1.getParent());
        assertTrue(parentMock.getChilds2().isEmpty());
    }
    
    
    @Test
    public void testSetOtherMasterChild() {
        MockEntityParent parentMock = new MockEntityParent();
        MockEntityChild2 mock1 = new MockEntityChild2();
        MockEntityOtherChild otherChild = new MockEntityOtherChild();
        parentFacade.getChildDetails().get(2).getMasterDetailHolder().getFunction().setMaster(parentMock, mock1);
        child3Facade.getChildDetails().get(0).getMasterDetailHolder().getFunction().setMaster(mock1, otherChild);
        assertEquals(mock1,otherChild.getParent());
        assertEquals(parentMock,mock1.getParent());
    }
    
    
    @Test
    public void testOtherGetDetails() {
        MockEntityParent parentMock = new MockEntityParent();
        MockEntityChild2 mock1 = new MockEntityChild2();
        MockEntityOtherChild otherChild = new MockEntityOtherChild();
        parentMock.setChilds2(Arrays.asList(mock1));
        mock1.setChilds(Arrays.asList(otherChild));
        assertEquals(1,parentFacade.getChildDetails().get(2).getMasterDetailHolder().getFunction().getDetails(parentMock).size());
        assertEquals(mock1,parentFacade.getChildDetails().get(2).getMasterDetailHolder().getFunction().getDetails(parentMock).get(0));
        assertEquals(otherChild,child3Facade.getChildDetails().get(0).getMasterDetailHolder().getFunction().getDetails(mock1).get(0));
    }
    
    
}
