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

import com.fasterxml.jackson.annotation.*;
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
        name="Statistics Collection Item"
)
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class StatisticsCollectionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.STATISTICS_ELEMENT;
    public static final String DESCRIPTION_KEY = "description";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @Schema(hidden = true)
    @JsonProperty(DESCRIPTION_KEY)
    protected String description;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    public StatisticsCollectionDataRestEntity() {
    }

    public StatisticsCollectionDataRestEntity(final String id,
                                              final String description) {
        super(id);

        this.description = description;
    }

    public StatisticsCollectionDataRestEntity(final String id,
                                              final String description,
                                              final String uri) {
        super(id);

        this.description = description;
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }

    @Schema(
            name="Statistics Collection Item Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @JsonProperty(DESCRIPTION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String description;

        public Attributes(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "description='" + description + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Attributes getAttributes() {
        return new Attributes(
                description
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.description = (String) attributes.getOrDefault("description", null);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();
        links.put("self", uri);

        return links;
    }

    @Override
    public String toString() {
        return "StatisticsCollectionRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
