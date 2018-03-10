
package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.core.Pair;
import com.protectsoft.apiee.base.core.Relation;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.exceptions.RequestException;
import com.protectsoft.apiee.core.masterdetail.MasterDetail;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.protectsoft.apiee.base.interfaces.IResource;
import com.protectsoft.apiee.core.masterdetail.MoveOption;
import com.protectsoft.apiee.util.PagedList;
import com.protectsoft.apiee.util.SearchUtil;

/**
 *
 * @param <M> Master
 * @param <D> Detail
 */
public abstract class BaseSubResource<M extends BaseEntity, D  extends BaseEntity> extends Resource<M>  implements IResource<D>  {
        
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
        for(Pair<MasterDetail,Api<? extends BaseEntity>> pair:getService().getChildDetails()) {
            if(pair.getMasterDetailHolder().getChildClass().equals(super.getRelation().getChildClass())) {
                M parent = getService().find(super.getRelation().getParentId());
                if(pair.getMasterDetailHolder().getMoveOption().equals(MoveOption.ONE_TO_ONE)) {
                    pair.getMasterDetailHolder().setDetail(parent, entity);
                } else {
                    pair.getMasterDetailHolder().addDetail(parent, entity);
                }
                pair.getMasterDetailHolder().setMaster(parent, entity);
                Api<D> api = (Api<D>)pair.getApi();
                api.create(entity);
                break;
            }
        }
        return Response
                .created(getNewPath(ui,entity))
                .entity(entity)
                .build();
    }
    

    @Override
    public D edit(Long id, D entity) {
        for(Pair<MasterDetail,Api<? extends BaseEntity>> pair:getService().getChildDetails()) {
            if(pair.getMasterDetailHolder().getChildClass().equals(super.getRelation().getChildClass())) {
                Api<D> api = (Api<D>)pair.getApi();
                return api.update(id, entity);
            }
        }
        throw new RuntimeException();
    }

    
    @Override
    public List<D> findRange(Integer from, Integer to) {
        return getDetails().subList(from, to);
    }

    @Override
    public Object remove(Long id) {
         for(Pair<MasterDetail,Api<? extends BaseEntity>> pair:getService().getChildDetails()) {
            if(pair.getMasterDetailHolder().getChildClass().equals(super.getRelation().getChildClass())) {
                Api<D> api = (Api<D>)pair.getApi();
                D detail = api.find(id);
                M parent = getService().find(super.getRelation().getParentId());
                if(pair.getMasterDetailHolder().getMoveOption().equals(MoveOption.ONE_TO_ONE)) {
                    pair.getMasterDetailHolder().removeDetail(parent,detail);
                } else {
                    pair.getMasterDetailHolder().setDetail(parent, detail);
                }
                api.delete(detail);
                return Response.noContent().build();
            }
        }
        throw new RuntimeException();
    }

    
    @Override
    public Integer count() {
        return getDetails().size();
    }
    
    @Override
    public List<D> search(ContainerRequestContext ctx, JsonObject search_clauses) {
        PagedList<D> result = SearchUtil.searchDetails(getDetails(), search_clauses);
        ctx.setProperty("X-Total-Count", result.getOriginalSize());
        return result.getFilteredList();
    }
    
    
    
    //maybe eager loaded/init it once on constructor?????
    private <M extends BaseEntity> List<D> getDetails() {
        List<D> details = new ArrayList<>();
        for(Pair<MasterDetail,Api<? extends BaseEntity>> pair:getService().getChildDetails()) {
            if(pair.getMasterDetailHolder().getChildClass().equals(super.getRelation().getChildClass())) {
                details = pair.getMasterDetailHolder().getDetails(getService().find(super.getRelation().getParentId()));
                break;
            }
        }
        return details;
    }

    
}
