package com.protectsoft.apiee.base.endpoint.spring;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.endpoint.BaseResource;
import com.protectsoft.apiee.base.endpoint.interfaces.ISpringResource;
import com.protectsoft.apiee.base.entities.BaseEntity;
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
 * @param <T>
 */
@RestController
public class ApiSpringResource<T extends BaseEntity> extends BaseResource<T> implements ISpringResource<T> {

    public ApiSpringResource(Api<T> service) {
       super(service);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Override
    public ResponseEntity<T> createEntity(@RequestBody T entity) {
        super.create(entity);
        return ResponseEntity
                .created(super.getNewPathSpring(entity))
                .body(entity);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public List<T> findAll() {
        return super.findAll();
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "{id}")
    @Override
    public T find(@PathVariable("id") Long id) {
        return super.find(id);
    }
    
    
    @RequestMapping(method = RequestMethod.PUT,value = "{id}")
    @Override
    public ResponseEntity<T> edit(@PathVariable("id") Long id,@RequestBody T entity) {
        return ResponseEntity.ok(super.implementEdit(id, entity));
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    @Override
    public ResponseEntity remove(@PathVariable("id") Long id) {
        super.implementRemove(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @RequestMapping(method = RequestMethod.GET,value = "{from}/{to}")
    @Override
    public List<T> findRange(@PathVariable("from") Integer from,@PathVariable("to") Integer to) {
        return super.findRange(from, to);
    }
    

    @Override
    @RequestMapping(method = RequestMethod.POST,value = "search")
    public List<T> search(HttpServletRequest request, JsonObject search) {
        return super.search((ContainerRequestContext) request, search);
    }
    
}
