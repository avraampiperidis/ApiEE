package com.protectsoft.apiee.core;

import com.protectsoft.apiee.core.Api;
import com.protectsoft.apiee.entities.BaseEntity;
import com.protectsoft.apiee.entities.BaseEntityAUTO;
import com.protectsoft.apiee.entities.BaseEntitySequence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Abraham Piperidis
 */
public class TestApi {
    
    
    @Test
    public void testApi() {
        Api<BaseEntityAUTO> api = new Api<BaseEntityAUTO>(BaseEntityAUTO.class) {};
        assertTrue(api.getChildDetails().isEmpty());
        assertEquals(BaseEntityAUTO.class,api.getEntityClass());
        assertTrue(!api.getEntitySimpleName().isEmpty());
    }
    
    
    @Test
    public void testApiParent() {
        Api<BaseEntity> api = new Api<BaseEntity>(BaseEntity.class) {};
        assertEquals(api,api.getParent());
        assertEquals(api,api.getParent().getParent());
        assertEquals(api,api.getParent().getParent().getParent());
        assertEquals(api,api.getParent().getParent().getParent().getParent());
        assertEquals(api.getEntityClass(),api.getParent().getEntityClass());
        
    }
 
    
    @Test
    public void testSetParent() {
        Api<BaseEntityAUTO> api = new Api<BaseEntityAUTO>(BaseEntityAUTO.class) {};
        Api<BaseEntitySequence> parent = getBaseEntitySequenceMockApi(BaseEntitySequence.class);
        assertTrue(parent.getChildDetails().isEmpty());
        parent.addChildDetail(null, null, null, api, null);
        assertTrue(!api.getParent().equals(api));
        assertEquals(parent,api.getParent());
        assertEquals(parent,api.getParent().getParent());
        assertEquals(api,parent.getChildDetails().get(0).getApi());
        assertTrue(api.getChildDetails().isEmpty());
        
    }
    
    
    @Test
    public void testAddChild() {
        Api<BaseEntity> parent = new Api<BaseEntity>(BaseEntity.class) {};
        Api<BaseEntity> child1 = new Api<BaseEntity>(BaseEntity.class) {};
        Api<BaseEntityAUTO> child2 = new Api<BaseEntityAUTO>(BaseEntityAUTO.class) {};
        parent.addChildDetail(null, null, null, child1, null);
        parent.addChildDetail(null, null, null, child2, null);
        assertEquals(2,parent.getChildDetails().size());
        assertEquals(parent,parent.getChildDetails().get(0).getApi().getParent());
        assertEquals(parent,parent.getChildDetails().get(1).getApi().getParent());
        assertEquals(parent,parent.getParent());
        assertEquals(parent,parent.getChildDetails().get(1).getApi().getParent().getParent());
    }
    
    @Test
    public void testAddParentChild() {
        Api<BaseEntityAUTO> parent = new Api<BaseEntityAUTO>(BaseEntityAUTO.class) {};
        Api<BaseEntity> child = new Api<BaseEntity>(BaseEntity.class) {};
        Api<BaseEntityAUTO> subChild = new Api<BaseEntityAUTO>(BaseEntityAUTO.class) {};
        Api<BaseEntity> subChild2 = new Api<BaseEntity>(BaseEntity.class) {};
        parent.addChildDetail(null, null, null, child, null);
        child.addChildDetail(null, null, null, subChild, null);
        child.addChildDetail(null, null, null, subChild2, null);
        
        assertEquals(1,parent.getChildDetails().size());
        assertEquals(child,parent.getChildDetails().get(0).getApi());
        assertEquals(parent,child.getParent());
        assertEquals(parent,child.getParent().getParent());
        
        assertEquals(2,child.getChildDetails().size());
        assertEquals(child,subChild.getParent());
        assertEquals(child,subChild2.getParent());
        assertEquals(parent,subChild2.getParent().getParent());
        assertEquals(parent,subChild2.getParent().getParent().getParent());
        assertEquals(parent,subChild.getParent().getParent());
        assertTrue(subChild.getChildDetails().isEmpty());
    }
    
    
    @Test
    public void testApiGetService() {
        Api<BaseEntityAUTO> api = new Api<BaseEntityAUTO>(BaseEntityAUTO.class) {};
        assertEquals(api,api.getService());
    }
    
    
    private  Api<BaseEntitySequence> getBaseEntitySequenceMockApi(Class clazz) {
        return new Api<BaseEntitySequence>(clazz) {};
    }
}
