
package com.protectsoft.apiee.remote;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * <b>Singleton pattern</b>
 * @see http://www.oracle.com/technetwork/java/servicelocator-137181.html
 * ...creating a JNDI initial context object and performing a lookup on an EJB home 
 * object utilizes significant resources. 
 * If multiple clients repeatedly require the same bean home object, 
 * uch duplicate effort can negatively impact application performance.
 */
public class ServiceLocator {
    
    
    private static ServiceLocator sl;
    private InitialContext context = null;
    
    private ServiceLocator() {
        try {
            context = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Just null it
     * in the next(getInstance()) call it will be re initialized
     */
    public static void refreshInitialContext() {
        synchronized(ServiceLocator.class){
            getInstance().context = null;
            sl = null;
        }
    }
    
    public static ServiceLocator getInstance() {
        if(sl == null){
            synchronized(ServiceLocator.class){
                sl = new ServiceLocator();
            }
        }
        return sl;
    }
    
    
    /**
     * Lookup's under java:module/bean.getSimpleName()
     * @param <T>
     * @param bean
     * @return the lookup service OR Null
     */
    public <T extends Object> T getModuleBeanService(Class bean){
        try {
            return (T) context.lookup("java:module/"+bean.getSimpleName());
        } catch (NamingException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * lookup under java:global/module/ean.getSimpleName()
     * @param <T>
     * @param bean
     * @param module
     * @return the lookup service OR Null
     */
    public <T extends Object> T getGlobalModuleBeanService(Class bean,String module){
        try {
            return (T)context.lookup("java:global/"+module+"/"+bean.getSimpleName());
        } catch (NamingException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    /**
     * If module == null || module.isEmpty()
     *  Lookup under java:module/beanClassImplementation!interface_class_path
     * ELSE
     *  Lookup under java:global/module/beanClassImplementation!interface_class_path
     * 
     * @param <T>
     * @param api
     * @param implementor
     * @param module
     * @return the lookup service OR Null
     */
    public <T extends Object> T getService(Class api,String implementor,String module){
        try {
            String lookup;
            if(module == null || module.isEmpty()){
                lookup = "java:module/"+implementor+"!"+api.getCanonicalName();
            } else {
                lookup = "java:global/"+module+"/"+implementor+"!"+api.getCanonicalName();
            }
            Object service = context.lookup(lookup);
            return (T)service;
        } catch (NamingException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
