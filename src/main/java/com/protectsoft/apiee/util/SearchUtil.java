package com.protectsoft.apiee.util;

import com.protectsoft.apiee.entities.BaseEntity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;


/**
 *
 */
public class SearchUtil {
    
    /**
     * 
     * @param <D>
     * @param list
     * @param json search parameters
     * @return 
     */
    public static <D extends BaseEntity>  PagedList<D> searchDetails(List<D> list, JsonObject json) {
        List<D> res = new ArrayList<>();
        int limit = 0;
        int offset = 0;
        List<SortBundle> sortBundles = new ArrayList<>();
        boolean hasNameFilter = false;
        for(String name:json.keySet()) {
            switch(name) {
                case "limit":
                    limit = json.getInt(name);
                    break;
                case "offset":
                    offset = json.getInt(name);
                    break;
                case "sort":
                    json.getJsonArray(name).getValuesAs(JsonString.class)
                            .stream().map((js) -> js.getString())
                            .filter((s) -> (s.startsWith("+") || s.startsWith("-")))
                            .map((s) -> {
                                SortBundle sortBundle = new SortBundle();
                                sortBundle.asc = s.startsWith("+");
                                sortBundle.field =  s.substring(1);
                                        return sortBundle;
                                    }).forEachOrdered((sortBundle) -> {
                                        sortBundles.add(sortBundle);
                                    });
                        break;
                default:
                    hasNameFilter = true;
                    for(D d: list){              
                        //filling res with objects that matches
                        setDeclareMethods(d,res,name,json);
                    }
                    break;
            }
        }
        
        if(!hasNameFilter && res.isEmpty()){
            res = list;
        }
        //sort
        if(!sortBundles.isEmpty()){
            GenericComparator comparator = new GenericComparator(sortBundles.get(0).field,sortBundles.get(0).asc);
            for(int i =1; i < sortBundles.size(); i++){
                comparator.thenComparing(new GenericComparator(sortBundles.get(i).field,sortBundles.get(i).asc));
            }
            Collections.sort(res,comparator);
        }
         //cut offset
        if(offset > 0 || limit > 0){
            return  new PagedList(ApiUtils.getLimitOffset(res, limit, offset),(long)list.size());
        }
        
        return new PagedList(res,(long)list.size());
    }
    
    
    /**
     *  this method need's to be simplified
     * @param <D>
     * @param d
     * @param res
     * @param name
     * @param json 
     */
    private static <D extends BaseEntity> void setDeclareMethods(Object d, List<D> res,String name,JsonObject json) {
        List<Method> allMethods = getAllMethods(d);
        for(Method method : allMethods){
            if(Modifier.isPublic(method.getModifiers())
                && method.getParameterTypes().length == 0
                && method.getReturnType() != void.class
                && ( (method.getName().startsWith("get") && method.getName().equalsIgnoreCase("get"+name)) 
                || (method.getName().startsWith("is") && method.getName().equalsIgnoreCase("is"+name)))
                )
                { 
                    if(isMethodNumber(method)){                       
                        try {             
                            if(extractJsonValue(name,json).toString().equals(method.invoke(d).toString())  && !res.contains(d)){
                                res.add((D) d);
                            }
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            //LOG
                        }
                    break;    
                    } else if(method.getReturnType() == String.class){                       
                        try {
                            String value = (String) method.invoke(d);
                            if(value != null && !value.isEmpty()){
                                if(!res.contains(d) && value.contains((String)extractJsonValue(name,json))){
                                    res.add((D)d);
                                }
                            }
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        }
                        break;                           
                    } else if(method.getReturnType() == boolean.class
                       || method.getReturnType() == Boolean.class
                     ){                                               
                     try {
                        if(Objects.equals((Boolean)method.invoke(d), (Boolean)extractJsonValue(name,json)) 
                                && !res.contains(d)){
                            res.add((D)d);
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    }                                                
                break;
                }                                     
            }
        }       
    }
    
    
    private static Object extractJsonValue(String name,JsonObject json){
        if(json.get(name) instanceof JsonString){
            return json.getString(name);
        } else if(json.get(name) instanceof JsonNumber){
            if(json.getJsonNumber(name).isIntegral()){
                return json.getJsonNumber(name).longValueExact();
            } else {
                return json.getJsonNumber(name).bigDecimalValue();
            }
        } else if(json.get(name).equals(JsonValue.FALSE)){
            return false;
        } else if(json.get(name).equals(JsonValue.TRUE)){
            return true;
        }
                
        return null;
    }
    
    
    
     private static class SortBundle {
        boolean asc;
        String field;
    }
     
     
     /**
      * Alternative solution to check only if its number
      * But with number byte,char,boolean will pass.
     * @param method
     * @return 
     */
    private static boolean isMethodNumber(Method method) {
       return method.getReturnType() == int.class ||
               method.getReturnType() == Integer.class
               || method.getReturnType() == long.class
               || method.getReturnType() == Long.class
               || method.getReturnType() == double.class
               || method.getReturnType() == Double.class
               || method.getReturnType() == float.class
               || method.getReturnType() == Float.class
               || method.getReturnType() == short.class
               || method.getReturnType() == Short.class;     
    }
    
    private static List<Method> getAllMethods(Object d) {
        List<Method> allMethods = new ArrayList<>();
        Class<?> c = d.getClass();
        while(c != null && c != Object.class){
            allMethods.addAll(Arrays.asList(c.getDeclaredMethods()));
            c = c.getSuperclass();
        }
        return allMethods;
    }
   
     
}
