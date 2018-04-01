package com.protectsoft.apiee.core.transformation;

/**
 *
 * @author Abraham Piperidis
 */
class Builder {
    
    private Transformer transformer;
    
    public Builder(Transformer transformer){
        this.transformer = transformer;
    }
    
    public Transform build() {
        return transformer.getInstance();
    }
}
