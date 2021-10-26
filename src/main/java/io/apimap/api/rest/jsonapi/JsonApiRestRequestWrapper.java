package io.apimap.api.rest.jsonapi;

import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.DataRestEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public class JsonApiRestRequestWrapper<T> {

    @Schema(description = "Resource wrapped in a data layer")
    @JsonView(JsonApiViews.Default.class)
    protected T data;

    public JsonApiRestRequestWrapper() {
    }

    public JsonApiRestRequestWrapper(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
