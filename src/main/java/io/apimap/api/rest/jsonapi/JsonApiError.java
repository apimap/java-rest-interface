package io.apimap.api.rest.jsonapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonApiError {
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "The HTTP status code applicable to this problem.")
    private String status;

    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "A short, human-readable summary of the problem.")
    private String title;

    public JsonApiError(String status, String title) {
        this.status = status;
        this.title = title;
    }

    public JsonApiError() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
