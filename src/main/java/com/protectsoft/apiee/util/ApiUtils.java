
package com.protectsoft.apiee.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * static Util helper methods
 */
public class ApiUtils {       
    
    public static String getMethodName(boolean isGet,String fieldName) {
        String setGet = null;
        if(isGet){
            setGet = "get";
        }else {
            setGet = "set";
        }
        return setGet+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }
    
    public static List<?> getLimitOffset(List<?> list,int limit,int offset){
        final List finalList = new ArrayList<>();
        for(int i =0; i <= offset+limit; i++){
            if(i == list.size()){
                break;
            }
            if(i >= offset && finalList.size() < limit){
                finalList.add(list.get(i));
            }
        }
        return finalList;
    }
    
    
    /**
     * 
     * @param clazz 
     * @param targetClass 
     * @return 
     */
    public static boolean isClassTypeOf(Class clazz,Class targetClass) {
        if(clazz.equals(targetClass)) {
            return true;
        }
        if(clazz.equals(Object.class)) {
            return false;
        }
        return isClassTypeOf(clazz,targetClass.getSuperclass());
    }
    
}
