
package com.protectsoft.apiee.core;

import java.util.Arrays;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 */
public class ApiEEUtilsTest {
    
    
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
