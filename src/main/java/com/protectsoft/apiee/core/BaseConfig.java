
package com.protectsoft.apiee.core;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * middleware for when the web app starts
 * 
 */
public class BaseConfig extends ResourceConfig  {
    
    public BaseConfig() {
         super();
         packages("com.protectsoft.apiee.core.exception;"
                + "com.protectsoft.apiee.core.transformation;"
                + "com.protectsoft.apiee.base.endpoint;" 
         );
        register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
    }
        
    public BaseConfig(String packages,Class<?>... classes){
        this();
        packages(packages);
        for(Class<?> c: classes){
            register(c);
        }
       
        register(StartUpHandler.class);
    }
    
}
