package com.protectsoft.apiee.endpoint.spring;

import com.protectsoft.apiee.core.Api;
import com.protectsoft.apiee.core.endpoint.BaseSubResource;
import com.protectsoft.apiee.endpoint.interfaces.ISpringResource;
import com.protectsoft.apiee.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
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
public class ApiSpringSubResource<M extends BaseEntity, D  extends BaseEntity> 
        extends BaseSubResource<M, D> implements ISpringResource<D> {
    
    public ApiSpringSubResource(Long id,Api<M> service,Class<D> childClass) {
        super(id,service,childClass);
    }

    @RequestMapping(method = RequestMethod.GET,value = "count")
    @Override
    public Integer count() {
        return super.count();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public ResponseEntity<D> createEntity(@RequestBody D entity) {
        super.create(entity);
        return ResponseEntity.created(super.getNewPathSpring(entity))
                .build();
    }
    
    @RequestMapping(method = RequestMethod.PUT,value = "{id}")
    @Override
    public ResponseEntity<D> edit(@PathVariable("id") Long id,@RequestBody D entity) {
        return ResponseEntity.ok(super.implementEdit(id, entity));
    }
    
    @RequestMapping(method = RequestMethod.GET,value="id")
    @Override
    public D find(@PathVariable("id") Long id) {
        return super.find(id);
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    @Override
    public ResponseEntity remove(@PathVariable("id") Long id) {
        super.implementRemove(id);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public List<D> findAll() {
        return super.findAll();
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "{from}/{to}")
    @Override
    public List<D> findRange(@PathVariable("from") Integer from,@PathVariable("to") Integer to) {
        return super.findRange(from, to);
    }
    
    
    @RequestMapping(method = RequestMethod.POST,value = "search")
    @Override
    public List<D> search(HttpServletRequest request, JsonObject search) {
        return super.search((ContainerRequestContext) request, search);
    }

    
}
