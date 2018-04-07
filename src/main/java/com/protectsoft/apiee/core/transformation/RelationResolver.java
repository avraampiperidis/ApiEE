package com.protectsoft.apiee.core.transformation;

import com.protectsoft.apiee.base.endpoint.BaseEntityTransformationProvider;
import com.protectsoft.apiee.core.annotations.TransformBean;
import com.protectsoft.apiee.util.ApiUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Embedded;

/**
 *
 * @author Abraham Piperidis
 */
public class RelationResolver {
    
    private final Class<?> clazz;
    private final Object instance;
    
    public RelationResolver(Class<?> c,Object instance){
        this.clazz  =c;
        this.instance = instance;
    }

    /**
     * @return the clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }

    
    public TransformRelation getRalations(){
        TransformRelation mapper = new TransformRelation(instance,clazz);
        setRelations(clazz,instance,mapper);
        return mapper;
    }
    
    
    private void setRelations(Class<?> clazz,Object instance,TransformRelation mapper) {
        for(Field f:clazz.getDeclaredFields()){
            TransformRelation tr = new TransformRelation();
            tr.setType(f.getType());
            try {
                if(f.isAnnotationPresent(TransformBean.class)) {
                    tr.setMethod(clazz.getDeclaredMethod(ApiUtils.getMethodName(false,f.getName()),f.getType()));
                    tr.setInstance(instance);
                    tr.setFieldName(f.getName());
                    if(!mapper.getChilds().contains(tr)){
                        tr.setParent(mapper);
                        tr.setIsAnnotationPresent(true);
                        mapper.getChilds().add(tr);
                    }
                } else if(f.isAnnotationPresent(Embedded.class)) {
                    Method getMethod = clazz.getDeclaredMethod(ApiUtils.getMethodName(true,f.getName()));
                    Object emb = getMethod.invoke(instance);
                    if(emb != null) {
                        tr.setIsEmbedded(true);
                        tr.setFieldName(f.getName());
                        tr.setInstance(instance);
                        if(!mapper.getChilds().contains(tr)){
                            tr.setParent(mapper);
                            mapper.getChilds().add(tr);
                        }
                        setRelations(emb.getClass(),emb,tr);
                    }
                } 
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(BaseEntityTransformationProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
