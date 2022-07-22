package io.apimap.api.rest;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Vote",
        description = "Entity representing the value of one vote"
)
public class VoteDataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.VOTE_ELEMENT;
    public static final String RATING_KEY = "rating";

    @JsonProperty(RATING_KEY)
    @Schema(hidden = true)
    protected Integer rating;

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    public VoteDataRestEntity() {
    }

    public VoteDataRestEntity(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    @Schema(
            name="Vote Attributes",
            description = "Object attributes. This object must be used when doing a POST"
    )
    public static class Attributes {
        @JsonProperty(RATING_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected Integer rating;

        public Attributes(Integer rating) {
            this.rating = rating;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "rating=" + rating +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public VoteDataRestEntity.Attributes getAttributes() {
        return new VoteDataRestEntity.Attributes(
                rating
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.rating = (Integer) attributes.getOrDefault(RATING_KEY, null);
    }

    @Override
    public String toString() {
        return "VoteDataRestEntity{" +
                "rating=" + rating +
                ", type='" + type + '\'' +
                '}';
    }
}
