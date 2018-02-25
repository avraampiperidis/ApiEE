package com.protectsoft.apiee.core;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abraham Piperidis
 */
public class Mock {
    
    public static class MockEntityParent extends BaseEntityAUTO {
        private List<MockEntityChild> childs = new ArrayList<>();
        private List<MockEntityChild2> childs2 = new ArrayList<>();

        /**
         * @return the childs
         */
        public List<MockEntityChild> getChilds() {
            return childs;
        }

        /**
         * @param childs the childs to set
         */
        public void setChilds(List<MockEntityChild> childs) {
            this.childs = childs;
        }

        /**
         * @return the childs2
         */
        public List<MockEntityChild2> getChilds2() {
            return childs2;
        }

        /**
         * @param childs2 the childs2 to set
         */
        public void setChilds2(List<MockEntityChild2> childs2) {
            this.childs2 = childs2;
        }
    }
    public static class MockEntityChild extends BaseEntityAUTO {
        private MockEntityParent parentMock;

        /**
         * @return the parentMock
         */
        public MockEntityParent getParent() {
            return parentMock;
        }

        /**
         * @param parent the parentMock to set
         */
        public void setParent(MockEntityParent parent) {
            this.parentMock = parent;
        }
    }
    public static class MockEntityChild2 extends BaseEntityAUTO {
        private MockEntityParent parentMock;

        /**
         * @return the parentMock
         */
        public MockEntityParent getParent() {
            return parentMock;
        }

        /**
         * @param parent the parentMock to set
         */
        public void setParent(MockEntityParent parent) {
            this.parentMock = parent;
        }
    }
    public static class MockFacadeParent extends Api<MockEntityParent> {
        public MockFacadeParent() {
            super(MockEntityParent.class);
        }
    }
    public static class MockFacadeChild extends Api<MockEntityChild> {
        public MockFacadeChild() {
            super(MockEntityChild.class);
        }
    }
    public static class MockFacadeChild2 extends Api<MockEntityChild2> {
        public MockFacadeChild2() {
            super(MockEntityChild2.class);
        }
    }
    
}
