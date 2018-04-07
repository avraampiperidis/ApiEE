package com.protectsoft.apiee.core.transformation;

import com.protectsoft.apiee.core.annotations.TransformBean;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 *
 * @author Abraham Piperidis
 */
public class IgnoreAnnotationIntrospector extends JacksonAnnotationIntrospector {
    

    public IgnoreAnnotationIntrospector() {
    }
    
    @Override
    public boolean hasIgnoreMarker(AnnotatedMember m) {
        return m.hasAnnotation(TransformBean.class);
    }
    
}
