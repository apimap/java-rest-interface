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
import io.apimap.rest.jsonapi.JsonApiRelationships;
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.rest.jsonapi.JsonApiViews;
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
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class ApiDataRestEntity extends DataRestEntity{

    public static final String TYPE = JsonApiRestResponseWrapper.API_ELEMENT;
    public static final String META_KEY = "meta";
    public static final String NAME_KEY = "name";
    public static final String CODE_REPOSITORY_KEY = "codeRepository";

    @Schema(hidden = true)
    @JsonProperty(META_KEY)
    @JsonView(JsonApiViews.Extended.class)
    protected ApiDataMetadataEntity meta;

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
    private JsonApiRelationships relationships;

    public ApiDataRestEntity() {
    }

    public ApiDataRestEntity(final String name) {
        super(name);

        this.name = name;
    }

    public ApiDataRestEntity(final String name,
                             final String codeRepository) {
        super(name);

        this.name = name;
        this.codeRepository = codeRepository;
    }

    public ApiDataRestEntity(final String name,
                             final String codeRepository,
                             final String uri,
                             final JsonApiRelationships relationships) {
        super(name);

        this.name = name;
        this.codeRepository = codeRepository;
        this.relationships = relationships != null ? (JsonApiRelationships) relationships.clone() : null;
        this.uri = uri;
    }

    public ApiDataRestEntity(final ApiDataMetadataEntity meta,
                             final String name,
                             final String codeRepository,
                             final String uri,
                             final JsonApiRelationships relationships) {
        super(name);

        this.meta = meta != null ? (ApiDataMetadataEntity) meta.clone() : null;
        this.name = name;
        this.codeRepository = codeRepository;
        this.uri = uri;
        this.relationships = relationships != null ? (JsonApiRelationships) relationships.clone() : null;
    }

    public ApiDataRestEntity(final ApiDataMetadataEntity meta,
                             final String name,
                             final String codeRepository,
                             final String uri,
                             final String type,
                             final JsonApiRelationships relationships) {
        super(name);

        this.meta = meta != null ? (ApiDataMetadataEntity) meta.clone() : null;
        this.name = name;
        this.codeRepository = codeRepository;
        this.uri = uri;
        this.type = type;
        this.relationships = relationships != null ? (JsonApiRelationships) relationships.clone() : null;
    }

    @Schema(
            name="API Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes implements Cloneable {
        @JsonProperty(NAME_KEY)
        @Schema(description = "API name",example = "Hello World", required = true)
        @JsonView(JsonApiViews.Default.class)
        protected String name;

        @JsonProperty(CODE_REPOSITORY_KEY)
        @Schema(description = "URL to the main API code repository",example = "http//github/helloworld", required = false)
        @JsonView(JsonApiViews.Default.class)
        protected String codeRepository;

        public Attributes() {
        }

        public Attributes(String name, String codeRepository) {
            this.name = name;
            this.codeRepository = codeRepository;
        }

        @Override
        public Object clone() {
            Attributes returnValue = null;

            try {
                returnValue = (Attributes) super.clone();
            } catch (CloneNotSupportedException e) {
                returnValue = new Attributes();
            }

            returnValue.name = this.name;
            returnValue.codeRepository = this.codeRepository;

            return returnValue;
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
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public JsonApiRelationships getRelationships() {
        return relationships != null ? (JsonApiRelationships) relationships.clone() : null;
    }

    public void setRelationships(JsonApiRelationships relationships) {
        this.relationships = relationships != null ? (JsonApiRelationships) relationships.clone() : null;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();

        if(uri != null) links.put("self", uri);

        if(links.size() < 1) return null;
        return links;
    }

    public ApiDataMetadataEntity getMeta() {
        return meta != null ? (ApiDataMetadataEntity) meta.clone() : null;
    }

    public void setMeta(ApiDataMetadataEntity meta) {
        this.meta = meta != null ? (ApiDataMetadataEntity) meta.clone() : null;
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
