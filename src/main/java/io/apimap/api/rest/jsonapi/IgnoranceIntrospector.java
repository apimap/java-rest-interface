package io.apimap.api.rest.jsonapi;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.lang.annotation.Annotation;

public class IgnoranceIntrospector extends JacksonAnnotationIntrospector {
    Boolean hasGenericParent = false;

    @Override
    protected boolean _hasAnnotation(Annotated ann, Class<? extends Annotation> annoClass) {
        hasGenericParent = hasGenericParent | ann.getRawType().equals(JsonApiRootObject.class);

        if(hasGenericParent
                && (annoClass.equals(com.fasterxml.jackson.annotation.JsonTypeInfo.class)
                || annoClass.equals(com.fasterxml.jackson.annotation.JsonTypeName.class))){
            return false;
        }

        return super._hasAnnotation(ann, annoClass);
    }

    @Override
    protected <A extends Annotation> A _findAnnotation(Annotated ann, Class<A> annoClass) {
        hasGenericParent = hasGenericParent | ann.getRawType().equals(JsonApiRootObject.class);

        if(hasGenericParent
            && (annoClass.equals(com.fasterxml.jackson.annotation.JsonTypeInfo.class)
                || annoClass.equals(com.fasterxml.jackson.annotation.JsonTypeName.class))){
            return null;
        }

        return super._findAnnotation(ann, annoClass);
    }
}
