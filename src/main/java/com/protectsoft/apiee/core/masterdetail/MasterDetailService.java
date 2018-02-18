package com.protectsoft.apiee.core.masterdetail;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.interfaces.IMasterDetail;
import java.util.List;

/**
 *
 * @param <M>
 * @param <D>
 */
public class MasterDetailService<M extends BaseEntity,D extends BaseEntity> implements IMasterDetail<M,D> {
    
    public MasterDetailService() {}

    @Override
    public void setChild(M m, D d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public D getChild(M m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<D> getChilds(M m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
