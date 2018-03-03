
package com.protectsoft.apiee.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
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
    
    
    
    @Test
    public void testConcatStringArrays() {
        String[] arr1 = new String[]{"one","two","three"};
        
        String[] data = ApiUtils.concatStringArrays(arr1);
        assertEquals(3,data.length);
        assertEquals("one",data[0]);
        
        String[] arr2 = new String[]{"four","five"};
        data = ApiUtils.concatStringArrays(arr2,arr1);
        assertEquals(5,data.length);
        assertEquals("four",data[0]);
        assertEquals("one",data[2]);
        
        data = ApiUtils.concatStringArrays(arr2,new String[]{null});
        assertEquals(null,data[2]);
        
        String[] arr3 = new String[]{"six","seven",null,"eight"};
        data = ApiUtils.concatStringArrays(arr1,arr2,arr3);
        
        assertEquals(arr1.length+arr2.length+arr3.length,data.length);
        
        String[] array1 = Arrays.asList(data).subList(0,arr1.length).toArray(new String[0]);
        String[] array2 = Arrays.asList(data).subList(3, arr2.length+3).toArray(new String[0]);
        String[] array3 = Arrays.asList(data).subList(5,arr3.length+5).toArray(new String[0]);
        
        Assert.assertArrayEquals(arr1,array1);
        Assert.assertArrayEquals(arr2,array2);
        Assert.assertArrayEquals(arr3,array3);
    }
    
}
