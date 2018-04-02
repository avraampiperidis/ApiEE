package com.protectsoft.apiee.core.transformation;

import com.protectsoft.apiee.base.core.RepoAccess;
import javax.json.JsonObject;

/**
 *
 * @author Abraham Piperidis
 */
public final class Transformer {
    
    private static Transformer transformer = null;
    private TransformRelation relation;
    private JsonObject json;
    private RepoAccess repo;
    
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
    
    public Transformer with(TransformRelation relations) {
        this.relation = relations;
        return this;
    }
    
    public Transformer with(RepoAccess repo) {
        this.repo = repo;
        return this;
    }
    
    public Transformer with(JsonObject json) {
        this.json = json;
        return this;
    }
    
    
    
    public void transform() {
         if(getRelation() == null || getJson() == null  || getRepo() == null){
            throw new NullPointerException("getRelation() == null || getJson() == null  || repo == null cannot be null");
        }
        new Builder(this).build();
    }
    

    /**
     * @return the relations
     */
    public TransformRelation getRelation() {
        return relation;
    }

    /**
     * @return the json
     */
    public JsonObject getJson() {
        return json;
    }

    /**
     * @return the repo
     */
    public RepoAccess getRepo() {
        return repo;
    }

   
    
}
