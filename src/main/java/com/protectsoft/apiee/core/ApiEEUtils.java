
package com.protectsoft.apiee.core;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

/**
 *
 * static Util helper methods
 */
public class ApiEEUtils {       
    
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
    
    
    
    public static Marshaller getMarshaller(Class clazz){
        try {
            Map<String, Object> properties = new HashMap<>();
            properties.put(JAXBContextProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            JAXBContext context = JAXBContextFactory.createContext(new Class[]{clazz}, properties);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            return marshaller;
        } catch (JAXBException ex) {
            Logger.getLogger(ApiEEUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
     public static String getStackTraceString(Throwable ex) {
         StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw);
         ex.printStackTrace(pw);
         return sw.toString();
     }
     
     
    
     
     /**
     * Ενώνει τα array απο String με την σειρα που του δήνωνται
     * 
     * @param arr
     * @return 
     */
    public static String[] concatStringArrays(String[]... arr) {
        List<String> data = new ArrayList<>();
        for(String[] array:arr) {
            data.addAll(Arrays.asList(array));
        }
        
        return data.toArray(new String[0]);
    }
    
    
}
