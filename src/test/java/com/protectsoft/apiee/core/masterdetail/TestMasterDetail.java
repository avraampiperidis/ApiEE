/*
 * The MIT License
 *
 * Copyright 2018 piper.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
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
        
        assertEquals(3,md.getFunction().getDetails(parent).size());
        MockEntityChild child = new MockEntityChild();
        assertNull(child.getParent());
        md.getFunction().setMaster(parent, child);
        assertEquals(parent,child.getParent());
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
