
package com.protectsoft.apiee.core;

import com.protectsoft.apiee.util.ApiUtils;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 */
public class ApiEEUtilsTest {
    
    List<Integer> small = new ArrayList<>();
    List<Integer> med  = new ArrayList<>();
    List<Integer> big = new ArrayList<>();
    
    
    @Test
    public void testGetLimitOffset() {
        for(int i =1; i <= 100; i++){
            if(i<=10) {
                small.add(i);
            }
            if(i>10&&i<40) {
                med.add(i);
            }
            if(i>40){
                big.add(i);
            }
        }
        
        assertEquals(10,ApiUtils.getLimitOffset(small,10,0).size());
        assertEquals(5,ApiUtils.getLimitOffset(small,5,0).size());
        assertEquals(0,ApiUtils.getLimitOffset(small,0,0).size());
        assertEquals(5,ApiUtils.getLimitOffset(small,10,5).size());
        assertEquals(0,ApiUtils.getLimitOffset(small,10,10).size());
        assertEquals(6,ApiUtils.getLimitOffset(small,10,5).get(0));
        assertEquals(10,ApiUtils.getLimitOffset(small,10,5).get(4));
        
        assertEquals(24,ApiUtils.getLimitOffset(med,40,5).size());
        assertEquals(5,ApiUtils.getLimitOffset(big,5,50).size());
    }
    
    
    
    
    
}
