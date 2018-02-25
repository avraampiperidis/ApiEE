package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import com.protectsoft.apiee.base.entities.BaseEntitySequence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Abraham Piperidis
 */
public class TestApi {
    
    
    @Test(expected = RuntimeException.class)
    public void testApiEx() {
        new Api<BaseEntity>(String.class) {};
    }
    
    
    @Test
    public void testApi() {
        Api<BaseEntity> api = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        assertTrue(api.getChilds().isEmpty());
        assertEquals(BaseEntityAUTO.class,api.getEntityClass());
        assertTrue(!api.getEntitySimpleName().isEmpty());
    }
    
    
    @Test
    public void testApiParent() {
        Api<BaseEntity> api = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        assertEquals(api,api.getParent());
        assertEquals(api,api.getParent().getParent());
        assertEquals(api,api.getParent().getParent().getParent());
        assertEquals(api,api.getParent().getParent().getParent().getParent());
        assertEquals(api.getEntityClass(),api.getParent().getEntityClass());
        
    }
 
    
    @Test
    public void testSetParent() {
        Api<BaseEntity> api = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        Api<BaseEntity> parent = getMockApi(BaseEntitySequence.class);
        assertTrue(parent.getChilds().isEmpty());
        parent.addChild(api);
        assertTrue(!api.getParent().equals(api));
        assertEquals(parent,api.getParent());
        assertEquals(parent,api.getParent().getParent());
        assertEquals(api,parent.getChilds().get(0).getApi());
        assertTrue(api.getChilds().isEmpty());
        
    }
    
    
    @Test
    public void testAddChild() {
        Api<BaseEntity> parent = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        Api<BaseEntity> child1 = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        Api<BaseEntity> child2 = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        parent.addChild(child1);
        parent.addChild(child2);
        assertEquals(2,parent.getChilds().size());
        assertEquals(parent,parent.getChilds().get(0).getApi().getParent());
        assertEquals(parent,parent.getChilds().get(1).getApi().getParent());
        assertEquals(parent,parent.getParent());
        assertEquals(parent,parent.getChilds().get(1).getApi().getParent().getParent());
    }
    
    @Test
    public void testAddParentChild() {
        Api<BaseEntity> parent = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        Api<BaseEntity> child = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        Api<BaseEntity> subChild = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        Api<BaseEntity> subChild2 = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        parent.addChild(child);
        child.addChild(subChild);
        child.addChild(subChild2);
        
        assertEquals(1,parent.getChilds().size());
        assertEquals(child,parent.getChilds().get(0).getApi());
        assertEquals(parent,child.getParent());
        assertEquals(parent,child.getParent().getParent());
        
        assertEquals(2,child.getChilds().size());
        assertEquals(child,subChild.getParent());
        assertEquals(child,subChild2.getParent());
        assertEquals(parent,subChild2.getParent().getParent());
        assertEquals(parent,subChild2.getParent().getParent().getParent());
        assertEquals(parent,subChild.getParent().getParent());
        assertTrue(subChild.getChilds().isEmpty());
    }
    
    
    @Test
    public void testApiGetService() {
        Api<BaseEntity> api = new Api<BaseEntity>(BaseEntityAUTO.class) {};
        assertEquals(api,api.getService());
    }
    
    
    private <T extends BaseEntity> Api<T> getMockApi(Class clazz) {
        return new Api<T>(clazz) {};
    }
}
