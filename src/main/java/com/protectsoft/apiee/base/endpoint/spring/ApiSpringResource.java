package com.protectsoft.apiee.base.endpoint.spring;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apiee.base.endpoint.BaseResource;
import com.protectsoft.apiee.base.endpoint.interfaces.ISpringResource;
import com.protectsoft.apiee.base.entities.BaseEntity;
import java.util.List;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<T> create(@Autowired HttpServletRequest request,@RequestBody T entity) {
        super.create(entity);
        return ResponseEntity
                .created(super.getNewPathSpring(entity))
                .body(entity);
    }

    @Override
    public List<T> search(HttpServletRequest request, JsonObject search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
