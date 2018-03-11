package com.protectsoft.apiee.remote;

/**
 *
 * @author Abraham Piperidis
 */
public class RemoteApi {

    
    public RemoteApi() {}
    
    /**
     * Lookup's under java:module/bean.getSimpleName()
     * @param <T>
     * @param bean
     * @return 
     */
    public static <T extends Object> T getModuleBeanService(Class bean){
        return ServiceLocator.getInstance().<T>getModuleBeanService(bean);
    }
    
    /**
     * lookup under java:global/module/ean.getSimpleName()
     * @param <T>
     * @param bean
     * @param module
     * @return 
     */
    public static <T extends Object> T getGlobalModuleBeanService(Class bean,String module) {
        return ServiceLocator.getInstance().<T>getGlobalModuleBeanService(bean, module);
    }
    
    /**
     * If module == null || module.isEmpty()
     *  Lookup under java:module/beanClassImplementation!interface_class_path
     * ELSE
     *  Lookup under java:global/module/beanClassImplementation!interface_class_path
     * @param <T>
     * @param inter
     * @param implementation
     * @param module
     * @return 
     */
    public static <T extends Object> T getService(Class inter,String implementation,String module) {
        return ServiceLocator.getInstance().<T>getService(inter, implementation, module);
    }
    
    
    public static <T extends Object> T get(Class<?> bean, String module, String implementation) {
        if(module.isEmpty() && implementation.isEmpty()) {
            return getModuleBeanService(bean);
        } else if(implementation.isEmpty() && !module.isEmpty()) {
            return getGlobalModuleBeanService(bean,module);
        }
        return getService(bean,implementation,module);
    }
    
}
