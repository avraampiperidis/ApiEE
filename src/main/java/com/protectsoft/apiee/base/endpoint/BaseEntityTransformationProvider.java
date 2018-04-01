package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.core.transformation.IgnoreAnnotationIntrospector;
import com.protectsoft.apiee.core.transformation.Transform;
import com.protectsoft.apiee.core.annotations.TransformBean;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.transformation.TransformRelation;
import com.protectsoft.apiee.util.ApiUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abraham Piperidis
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class BaseEntityTransformationProvider implements MessageBodyReader<Transform> {
    

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return ApiUtils.isClassInstance(BaseEntity.class, type);
    }

  
    @Override
    public Transform readFrom(Class<Transform> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setAnnotationIntrospector(new IgnoreAnnotationIntrospector());
            
            Transform  t = mapper.readValue(entityStream, type);
            List<TransformRelation> relations = getRalations(type,t);
            JsonObject json = Json.createReader(entityStream).readObject();
            //do stuff...
            return  t;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BaseEntityTransformationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException();
    }
    
    /**
     * @Todo Fix,refactor simplify later...
     * 
     * @param clazz
     * @param instance
     * @return 
     */
    private List<TransformRelation> getRalations(Class<?> clazz,Transform instance){
        List<TransformRelation> list = new ArrayList<>();
        for(Field f:clazz.getDeclaredFields()){
            TransformRelation tr = new TransformRelation();
            tr.setType(f.getType());
            
            if(f.isAnnotationPresent(TransformBean.class)) {
                try {
                    tr.setMethod(clazz.getDeclaredMethod(ApiUtils.getMethodName(false,f.getName()),f.getType()));
                    list.add(tr);
                } catch (NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(BaseEntityTransformationProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(f.isAnnotationPresent(Embedded.class)) {
                try {
                    tr.setIsEmbedded(true);
                    tr.setMethod(clazz.getDeclaredMethod(ApiUtils.getMethodName(true,f.getName())));
                    list.add(tr);
                } catch (NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(BaseEntityTransformationProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(f.isAnnotationPresent(ManyToOne.class) || f.isAnnotationPresent(OneToOne.class)) {
                //also check for many to one and one to one
                //CHECKING FOR CYCLIC PREFERENCE IS NEEDED
            }
        }
        return list;
    }
    
    
    
}
