
package com.protectsoft.apiee.remote;


/**
 *
 */
public class SelfApi extends Api {

    public <T extends RemoteApi> T getApiService(Class aClass, String implementation) {
        return ServiceLocator.getInstance().<T>getService(aClass, implementation,null);
    }
    
    @Override
    public <T extends RemoteApi> T getApiService(Class aClass, String implementation, String module) {
       return ServiceLocator.getInstance().<T>getService(aClass, implementation,null);
    }
    
    
    public <T extends Object> T getBeanService(Class bean){
        return ServiceLocator.getInstance().<T>getModuleBeanService(bean);
    }
    
    public <T extends Object> T getBeanModuleService(Class bean,String module) {
        return ServiceLocator.getInstance().<T>getGlobalModuleBeanService(bean, module);
    }
    
    public  <T extends RemoteApi> T getBeanService(Class clazz,String implementation) {
        return ServiceLocator.getInstance().<T>getService(clazz, implementation, null);
    }

    
    
}
