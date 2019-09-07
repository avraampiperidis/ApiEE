package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.masterdetail.MasterDetailFunction;
import com.protectsoft.apiee.masterdetail.MasterDetail;
import com.protectsoft.apiee.masterdetail.MoveOption;
import com.protectsoft.apiee.masterdetail.OneToManyFunction;
import com.protectsoft.apiee.masterdetail.OneToOneFunction;
import com.protectsoft.apiee.masterdetail.RelationType;
import com.protectsoft.apiee.entities.BaseEntityAUTO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author piper
 */
public class TestMasterDetail {
    
    private  MasterDetail md;
    
    @Before
    public  void setup() {
         md = new MasterDetail(MockEntityChild.class,MockEntityParent.class,new MasterDetailFunction<MockEntityParent,MockEntityChild>() {
                @Override
                public List<MockEntityChild> getDetails(MockEntityParent parent) {
                     return parent.getChilds();
                };
                
                @Override
                public void setMaster(MockEntityParent parent,MockEntityChild child) {
                    child.setParent(parent);
                }
                
            },MoveOption.ORPHANS_ALLOWED){
        };
    }
    
    @Test
    public void testGetDetails() {
        MockEntityParent parent = new MockEntityParent();
        parent.addChild(new MockEntityChild());
        parent.addChild(new MockEntityChild());
        parent.addChild(new MockEntityChild());
        
        assertEquals(3,md.getDetails(parent).size());
        MockEntityChild child = new MockEntityChild();
        assertNull(child.getParent());
        md.setMaster(parent, child);
        assertEquals(parent,child.getParent());
    }
    
    @Test
    public void testGetRelationType() {
        md = new MasterDetail(MockEntityChild.class,MockEntityParent.class,new OneToOneFunction<MockEntityParent,MockEntityChild>() {
                @Override
                public MockEntityChild getDetail(MockEntityParent parent) {
                     return null;
                };
                
                @Override
                public void setDetail(MockEntityParent master, MockEntityChild detail) {
                }
                
                @Override
                public void setMaster(MockEntityParent parent,MockEntityChild child) {
                    child.setParent(parent);
                }
                
            },MoveOption.ORPHANS_ALLOWED){
        };
        
        assertEquals(RelationType.ONE_TO_ONE,md.getRelationType());
        
        md = new MasterDetail(MockEntityChild.class,MockEntityParent.class,new OneToManyFunction<MockEntityParent,MockEntityChild>() {
                @Override
                public List getDetails(MockEntityParent parent) {
                     return null;
                };
                
                @Override
                public void setMaster(MockEntityParent parent,MockEntityChild child) {
                    child.setParent(parent);
                }
                
            },MoveOption.ORPHANS_ALLOWED){
        };
        
        assertEquals(RelationType.ONE_TO_MANY,md.getRelationType());
    }
    
    //replace with mockito?
    private class MockEntityChild extends BaseEntityAUTO {
        private String name;
        private MockEntityParent parent;

        public MockEntityChild() {}

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        private void setParent(MockEntityParent parent) {
            this.parent = parent;
        }
        
        private MockEntityParent getParent() {
            return parent;
        }
       
        
    }
    
    private class MockEntityParent extends BaseEntityAUTO {
        private String name;
        private final List<MockEntityChild> childs;

        public MockEntityParent() {
            this.childs = new ArrayList<>();
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        private List<MockEntityChild> getChilds() {
            return childs;
        }
        
        private void addChild(MockEntityChild child) {
            this.childs.add(child);
        }
    }
}
