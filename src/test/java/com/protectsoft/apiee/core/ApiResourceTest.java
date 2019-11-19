/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apiee.core;

import com.protectsoft.apiee.core.MyMock.IService;
import com.protectsoft.apiee.core.MyMock.MockFacadeChild;
import com.protectsoft.apiee.endpoint.jaxrs.ApiResource;
import com.protectsoft.apiee.entities.BaseEntityAUTO;
import org.junit.Test;

/**
 *
 * @author piperidis.a
 */
public class ApiResourceTest {
    
    @Test
    public void testApiResource() {
        ApiResourceMock resource = new ApiResourceMock();
        Api<BaseEntityAUTO> service = resource.getService();
        System.out.println("service.getClass():"+service.getEntityClass());
    }
    
    public static class ApiResourceMock extends ApiResource<BaseEntityAUTO> {
        public ApiResourceMock() {
            super((IService)new MockFacadeChild());
        }
    }
    
}


