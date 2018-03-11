package com.protectsoft.apiee.core.producers;

import com.protectsoft.apiee.core.annotations.RemoteLookup;
import com.protectsoft.apiee.remote.RemoteApi;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Abraham Piperidis
 */
public class RemoteLookupProducer {
    
    public RemoteLookupProducer() {}
    
    @Produces
    @RequestScoped
    @RemoteLookup
    Object getBean(InjectionPoint ip) {
        RemoteLookup anno = ip.getAnnotated().getAnnotation(RemoteLookup.class);
        Class<?> bean = anno.clazz();
        String module = anno.module();
        String implementation = anno.implementation();
        return RemoteApi.get(bean,module,implementation);
    }
}
