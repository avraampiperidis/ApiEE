
package com.protectsoft.apiee.api;


/**
 *
 */
public abstract class Api {
        
    protected Api() {}
    
    public abstract <T extends RemoteApi> T getApiService(Class aClass, String implementation,String module);
    
    
    public static <T extends Api> T getApi(Class clazz){
        if(clazz.equals(ModuleApi.class)){
            return (T) new ModuleApi();
        } else if(clazz.equals(SelfApi.class)){
            return (T) new SelfApi();
        }
        
        return null;
    }
    
}

