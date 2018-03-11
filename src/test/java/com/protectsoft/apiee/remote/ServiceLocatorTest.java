package com.protectsoft.apiee.remote;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Abraham Piperidis
 */
@RunWith(Arquillian.class)
public class ServiceLocatorTest {

  
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(GreeterBean.class)
            .addClass(Inter.class)
            .addClass(MockBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetModuleBeanServiceNull() {
        Object bean = ServiceLocator.getInstance().getModuleBeanService(String.class);
        bean.hashCode();
    }
    
    @Test
    public void testGetModuleBeanService() {
        Object bean = ServiceLocator.getInstance().getModuleBeanService(GreeterBean.class);
        assertTrue(bean instanceof Inter);
        Inter gService = (Inter)bean;
        assertEquals("GreeterBean",gService.getMsg());
    }
    
    @Test
    public void testGetGlobalModuleBeanService() {
        Inter gService = ServiceLocator.getInstance().getGlobalModuleBeanService(GreeterBean.class,"test");
        assertEquals("GreeterBean",gService.getMsg());
    }
    
    
    @Test
    public void testGetSevice() {
        Inter gService = ServiceLocator.getInstance().getService(Inter.class, "GreeterBean", "test");
        assertEquals("GreeterBean",gService.getMsg());
        Inter mockService =  ServiceLocator.getInstance().getService(Inter.class, "MockBean", "test");
        assertEquals("MockBean",mockService.getMsg());
    }
    
}
