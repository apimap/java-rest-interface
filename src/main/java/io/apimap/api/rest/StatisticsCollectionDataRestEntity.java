/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
 */

package io.apimap.api.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Statistics Collection Item"
)
public class StatisticsCollectionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRootObject.STATISTICS_ELEMENT;
    public static final String DESCRIPTION_KEY = "description";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Base.class)
    protected String type = TYPE;

    @Schema(hidden = true)
    @JsonProperty(DESCRIPTION_KEY)
    protected String description;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    public StatisticsCollectionDataRestEntity() {
    }

    public StatisticsCollectionDataRestEntity(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public StatisticsCollectionDataRestEntity(String id, String description, String uri) {
        this.id = id;
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
    @JsonView(JsonApiViews.Base.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Attributes getAttributes() {
        return new Attributes(
                description
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.description = (String) attributes.getOrDefault("description", null);
    }

    @JsonProperty
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
