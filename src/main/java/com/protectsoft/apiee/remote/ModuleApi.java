
package com.protectsoft.apiee.remote;


/**
 *
 */
public class ModuleApi extends Api {

    @Override
    public <T extends RemoteApi> T getApiService(Class aClass, String implementation,String module) {
        return ServiceLocator.getInstance().<T>getService(aClass, implementation,module);
    }
    
     public <T extends Object> T getBeanModuleService(Class bean,String module) {
        return ServiceLocator.getInstance().<T>getGlobalModuleBeanService(bean, module);
    }

    
}
