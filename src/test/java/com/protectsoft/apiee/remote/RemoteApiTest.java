package com.protectsoft.apiee.remote;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Abraham Piperidis
 */
@RunWith(Arquillian.class)
public class RemoteApiTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(GreeterBean.class)
            .addClass(Inter.class)
            .addClass(MockBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Test
    public void testGetModuleBeanService() {
        Inter bean = RemoteApi.getModuleBeanService(GreeterBean.class);
        assertEquals("GreeterBean",bean.getMsg());
    }
    
    @Test
    public void testGetGlobalModuleBeanService() {
        Inter gService = RemoteApi.getGlobalModuleBeanService(GreeterBean.class,"test");
        assertEquals("GreeterBean",gService.getMsg());
    }
    
    @Test
    public void testGetSevice() {
        Inter gService = RemoteApi.<Inter>getService(Inter.class, "GreeterBean", "test");
        Inter mockService =  RemoteApi.getService(Inter.class, "MockBean", "test");
        assertEquals("GreeterBean",gService.getMsg());
        assertEquals("MockBean",mockService.getMsg());
    }
    
}
