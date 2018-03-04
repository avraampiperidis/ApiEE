
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.boundary.Api;
import com.protectsoft.apiee.base.boundary.Pair;
import com.protectsoft.apiee.base.boundary.Relation;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.base.interfaces.IReposirotyResource;
import com.protectsoft.apiee.core.exceptions.RequestException;
import com.protectsoft.apiee.core.masterdetail.MasterDetail;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
        return getDetails();
    }
    
    @Override
    public D find(Long id) {
        D d = getDetails().stream().filter(x->x.getId().equals(id))
                .findAny()
                .orElse(null);
        if(d == null) {
            throw new RequestException();
        }
        return d;
    }

    @Override
    public Response create(UriInfo ui, D entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public D edit(Long id, D entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<D> findRange(Integer from, Integer to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<D> search(UriInfo ui, ContainerRequestContext ctx, JsonObject search_clauses) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public Integer count() {
        return getDetails().size();
    }
    
    
    private List<D> getDetails() {
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
