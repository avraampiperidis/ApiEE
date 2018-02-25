package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.entities.BaseEntityAUTO;
import com.protectsoft.apiee.core.masterdetail.MasterDetail;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Abraham Piperidis
 */
public class TestPair {
        
    @Test
    public void testPair() {
        List<Pair<MasterDetail<?,?>,Api<?>>> pairs = new ArrayList<>();
        pairs.add(new Pair(new MasterDetail(BaseEntity.class,BaseEntity.class,null,null),new Api<BaseEntity>(BaseEntity.class){}));
        pairs.add(new Pair(new MasterDetail(BaseEntity.class,BaseEntityAUTO.class,null,null),new Api<BaseEntity>(BaseEntity.class){}));
        assertEquals(BaseEntity.class,pairs.get(0).getMasterDetailHolder().getChildClass());
        assertEquals(BaseEntity.class,pairs.get(0).getMasterDetailHolder().getMasterClass());
        assertEquals(BaseEntityAUTO.class,pairs.get(1).getMasterDetailHolder().getChildClass());
    }
    
}
