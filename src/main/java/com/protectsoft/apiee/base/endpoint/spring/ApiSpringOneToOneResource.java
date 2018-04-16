package com.protectsoft.apiee.base.endpoint.spring;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.endpoint.BaseSubResource;
import com.protectsoft.apiee.base.endpoint.interfaces.ISpringResource;
import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abraham Piperidis
 * @param <M>
 * @param <D>
 */
@RestController
public class ApiSpringOneToOneResource<M extends BaseEntity, D  extends BaseEntity> 
        extends BaseSubResource<M, D> implements ISpringResource<D> {
    
    public ApiSpringOneToOneResource(Long id,Api<M> service,Class<D> childClass) {
        super(id,service,childClass);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public Integer count() {
        throw new NotFoundException();
    }

    @RequestMapping(method = RequestMethod.POST)
    @Override
    public ResponseEntity<D> createEntity(@RequestBody D entity) {
        if(super.getDetail() != null){
            throw new EntityExistsException();
        }
        super.create(entity);
        return ResponseEntity.created(super.getNewPathSpring(entity))
                .build();
    }
    
    @RequestMapping(method = RequestMethod.PUT,value = "{id}")
    @Override
    public ResponseEntity<D> edit(@PathVariable("id") Long id,@RequestBody D entity) {
        throw new NotFoundException();
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<D> edit(@RequestBody D entity) {
        D db = super.getDetail();
        return ResponseEntity.ok(super.implementEdit(db.getId(), entity));
    }
    
    
    @RequestMapping(method = RequestMethod.GET,value = "{id}")
    @Override
    public D find(@PathVariable("id") Long id) {
        return super.getDetail();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public D find() {
        return super.getDetail();
    }
    
    @Override
    public List<D> findAll() {
        throw new NotFoundException();
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "{from}/{to}")
    @Override
    public List<D> findRange(@PathVariable("from") Integer from,@PathVariable("to") Integer to) {
        throw new NotFoundException();
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    @Override
    public ResponseEntity remove(@PathVariable("id") Long id) {
        throw new NotFoundException();
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity remove() {
        super.implementRemove(super.getDetail().getId());
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST,value = "search")
    @Override
    public List<D> search(HttpServletRequest request,@RequestBody JsonObject search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
