package com.protectsoft.apiee.core.transformation;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 *
 * @author Abraham Piperidis
 */
class Builder {
    
    private final Transformer transformer;
    
    public Builder(Transformer transformer){
        this.transformer = transformer;
    }
    
    public void build(Transform instance) {
        transformer.getRelation().getChilds().forEach((t) -> {
            this.process(t,t.getInstance(),transformer.getJson());
        });
    }
    
    
    private void process(TransformRelation t,Object instance,JsonObject json){
        if(t.isIsEmbedded()){
            t.getChilds().forEach((t2) -> {
                process(t2,t2.getInstance(),json.getJsonObject(t.getFieldName()));
            });
        }else if(json.containsKey(t.getFieldName()) && !json.isNull(t.getFieldName()) 
                && JsonValue.ValueType.NUMBER.equals(json.get(t.getFieldName()).getValueType())) {
            try {
                t.getMethod().invoke(instance,transformer.getRepo().getEntityManager().find(t.getType(),json.getJsonNumber(t.getFieldName()).longValueExact()));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
