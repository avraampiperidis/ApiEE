
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.boundary.Pair;
import com.protectsoft.apiee.base.boundary.Relation;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.interfaces.IReposirotyResource;
import com.protectsoft.apiee.core.masterdetail.MasterDetail;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <M> Master
 * @param <D> Detail
 */
public abstract class BaseSubResource<M extends BaseEntity, D  extends BaseEntity> extends Resource<M>  implements IReposirotyResource<D>  {
        
    protected BaseSubResource(Long id,Api<M> service,Class<D> childClass) {
        super(service,new Relation(id,childClass));
    }
    
    
    @Override
    public List<D> findAll() {
        List<D> details = new ArrayList<>();
        for(Pair<MasterDetail,Api<? extends BaseEntity>> pair:getService().getChildDetails()) {
            if(pair.getMasterDetailHolder().getChildClass().equals(super.getRelation().getChildClass())) {
                details = pair.getMasterDetailHolder().getFunction().getDetails(getService().find(super.getRelation().getParentId()));
                break;
            }
        }
        return details;
    }

}
