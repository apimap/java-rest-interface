package io.apimap.api.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.api.rest.jsonapi.JsonApiViews;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
public class ApiVersionRatingEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.API_META_ELEMENT;
    public static final String DOCUMENTATION_RATING_KEY = "documentation";

    @JsonView(JsonApiViews.Default.class)
    @JsonProperty(DOCUMENTATION_RATING_KEY)
    protected Integer documentation;

    public ApiVersionRatingEntity() {
    }

    public ApiVersionRatingEntity(Integer documentation) {
        this.documentation = documentation;
    }

    public Integer getDocumentation() {
        return documentation;
    }

    public void setDocumentation(Integer documentation) {
        this.documentation = documentation;
    }

    @Override
    public String toString() {
        return "ApiVersionRatingEntity{" +
                "documentation=" + documentation +
                '}';
    }
}
