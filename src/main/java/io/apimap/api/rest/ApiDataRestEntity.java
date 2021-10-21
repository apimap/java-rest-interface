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
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apimap.api.rest.jsonapi.JsonApiRelationships;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="API",
        description = "Core API entity used to describe an API"
)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonTypeName(value = "data")
public class ApiDataRestEntity extends DataRestEntity{
    public static final String TYPE = JsonApiRootObject.API_ELEMENT;
    public static final String META_KEY = "meta";
    public static final String NAME_KEY = "name";
    public static final String CODE_REPOSITORY_KEY = "codeRepository";

    @Schema(hidden = true)
    @JsonProperty(META_KEY)
    @JsonView(JsonApiViews.Extended.class)
    protected ApiDataApiMetadataEntity meta;

    @Schema(hidden = true)
    @JsonIgnore
    protected String name;

    @Schema(hidden = true)
    @JsonIgnore
    protected String codeRepository;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    @JsonView(JsonApiViews.Default.class)
    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    protected String type = TYPE;

    @Schema(hidden = true)
    @JsonIgnore
    protected JsonApiRelationships relationships;

    public ApiDataRestEntity() {
    }

    public ApiDataRestEntity(String name) {
        this.name = name;
    }

    public ApiDataRestEntity(String name, String codeRepository) {
        this.name = name;
        this.codeRepository = codeRepository;
    }

    public ApiDataRestEntity(String name, String codeRepository, String uri, JsonApiRelationships relationships) {
        this.name = name;
        this.codeRepository = codeRepository;
        this.relationships = relationships;

        this.id = name;
        this.uri = uri;
    }

    public ApiDataRestEntity(ApiDataApiMetadataEntity meta, String name, String codeRepository, String uri, JsonApiRelationships relationships) {
        this.meta = meta;
        this.name = name;
        this.codeRepository = codeRepository;
        this.uri = uri;
        this.relationships = relationships;
    }

    @Schema(
            name="API Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @JsonProperty(NAME_KEY)
        @Schema(description = "API name",example = "Hello World", required = true)
        @JsonView(JsonApiViews.Default.class)
        protected String name;

        @JsonProperty(CODE_REPOSITORY_KEY)
        @Schema(description = "URL to the main API code repository",example = "http//github/helloworld", required = false)
        @JsonView(JsonApiViews.Default.class)
        protected String codeRepository;

        public Attributes(String name, String codeRepository) {
            this.name = name;
            this.codeRepository = codeRepository;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "name='" + name + '\'' +
                    ", codeRepository='" + codeRepository + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    public Attributes getAttributes() {
        return new Attributes(
                name,
                codeRepository
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.name = (String) attributes.getOrDefault(NAME_KEY, null);
        this.codeRepository = (String) attributes.getOrDefault(CODE_REPOSITORY_KEY, null);
    }

    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public JsonApiRelationships getRelationships() {
        return this.relationships;
    }

    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();

        if(uri != null) links.put("self", uri);

        if(links.size() < 1) return null;
        return links;
    }

    public ApiDataApiMetadataEntity getMeta() {
        return meta;
    }

    public void setMeta(ApiDataApiMetadataEntity meta) {
        this.meta = meta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.id = name;
    }

    public String getCodeRepository() {
        return codeRepository;
    }

    public void setCodeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public String toString() {
        return "ApiDataRestEntity{" +
                "meta=" + meta +
                ", name='" + name + '\'' +
                ", codeRepository='" + codeRepository + '\'' +
                ", uri='" + uri + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", relationships=" + relationships +
                '}';
    }
}
