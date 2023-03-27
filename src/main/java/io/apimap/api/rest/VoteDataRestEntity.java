/*
Copyright 2021-2023 TELENOR NORGE AS

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package io.apimap.api.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.rest.jsonapi.JsonApiViews;
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
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
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
