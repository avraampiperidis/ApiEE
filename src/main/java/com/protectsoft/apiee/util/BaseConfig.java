
package com.protectsoft.apiee.util;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * middleware for when the web app starts
 * 
 */
public class BaseConfig extends ResourceConfig  {
    
    public BaseConfig() {
         super();
         register(JacksonJaxbJsonProvider.class);
         packages("com.protectsoft.apiee.core.endpoint;" 
                + "com.protectsoft.apiee.exceptions.providers;" 
                + "com.protectsoft.apiee.endpoint.jaxrs;" 
         );
    }
        
    public BaseConfig(String packages,Class<?>... classes){
        this();
        packages(packages);
        for(Class<?> c: classes){
            register(c);
        }       
    }
}
