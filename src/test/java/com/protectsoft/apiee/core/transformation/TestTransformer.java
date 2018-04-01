package com.protectsoft.apiee.core.transformation;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Abraham Piperidis
 */
public class TestTransformer {
    
    
    @Test
    public void TestGetTransformer() throws InterruptedException {
        List<Transformer> trans = new ArrayList<>();
        Runnable[] runs = new Runnable[100];
        for(int i =0; i < 100; i++){
            runs[i] = new Runnable() {
                @Override
                public void run() {
                    trans.add(Transformer.getTransformer());
                }
            };
        }
        for(Runnable r : runs){
            r.run();
        }
        assertTrue(trans.stream().allMatch(x->x.hashCode() == trans.get(0).hashCode()));
    }
    
    
    @Test(expected = NullPointerException.class)
    public void testTransformNull() {
        Transformer tr = Transformer.getTransformer();
        tr.transform();
    }
    
    
    @Test
    public void testTransform() {
        Transformer tr = Transformer.getTransformer();
        Transform t = tr.with(Json.createObjectBuilder().add("key","val").build())
                .with(new ArrayList<>())
                .with(new Transform() {
                }).transform();
        assertNotNull(t);
    }
    
}
