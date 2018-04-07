package com.protectsoft.apiee.base.endpoint;

import com.protectsoft.apiee.core.transformation.IgnoreAnnotationIntrospector;
import com.protectsoft.apiee.core.transformation.Transform;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.protectsoft.apiee.base.core.RepoAccess;
import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.transformation.RelationResolver;
import com.protectsoft.apiee.core.transformation.Transformer;
import com.protectsoft.apiee.util.ApiUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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
    
    @Inject
    private RepoAccess repo; 
    
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
            JsonObject json = Json.createReader(entityStream).readObject();
            Transform  t = mapper.readValue(json.toString(), type);
            
            Transformer.getTransformer()
                    .with(json)
                    .with(new RelationResolver(type,t).getRalations())
                    .with(repo)
                    .transform();
            
             return t;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BaseEntityTransformationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException();
    }
    

    
}
