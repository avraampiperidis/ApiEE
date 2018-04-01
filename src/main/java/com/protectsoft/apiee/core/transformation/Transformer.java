package com.protectsoft.apiee.core.transformation;

import java.util.List;
import javax.json.JsonObject;

/**
 *
 * @author Abraham Piperidis
 */
public final class Transformer {
    
    private static Transformer transformer = null;
    private List<TransformRelation> relations;
    private JsonObject json;
    private Transform instance;
    
    private Transformer() {}
    
    public static Transformer getTransformer() {
        if(transformer == null) {
            synchronized(Transformer.class) {
                if(transformer == null) {
                    transformer = new Transformer();
                }
            }
        }
        return transformer;
    }
    
    public Transformer with(List<TransformRelation> relations) {
        this.relations = relations;
        return this;
    }
    
    public Transformer with(JsonObject json) {
        this.json = json;
        return this;
    }
    
    public Transformer with(Transform instance) {
        this.instance = instance;
        return this;
    }
    
    public Transform transform() {
        if(getRelations() == null || getJson() == null || getInstance() == null){
            throw new NullPointerException("relations OR json OR instance cannot be null");
        }
        return new Builder(this).build();
    }

    /**
     * @return the relations
     */
    public List<TransformRelation> getRelations() {
        return relations;
    }

    /**
     * @return the json
     */
    public JsonObject getJson() {
        return json;
    }

    /**
     * @return the instance
     */
    public Transform getInstance() {
        return instance;
    }
    
    
}
