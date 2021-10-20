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
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiRelationships;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
    getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
    name="API Collection Item",
    description = "Entity used to return lists of APIs"
)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME, property = "type", visible=true)
@JsonTypeName(value = "data")
public class ApiCollectionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRootObject.API_ELEMENT;
    public static final String NAME_KEY = "name";
    public static final String CODE_REPOSITORY_KEY = "codeRepository";
    public static final String VERSION_KEY = "version";
    public static final String STATUS_KEY = "status";
    public static final String DESCRIPTION_KEY = "description";
    public static final String DOCUMENTATION_KEY = "documentation";

    @Schema(hidden = true)
    @JsonIgnore
    protected ApiDataApiMetadataEntity meta;

    @Schema(hidden = true)
    @JsonIgnore
    protected String name;

    @Schema(hidden = true)
    @JsonIgnore
    protected String codeRepository;

    @Schema(hidden = true)
    @JsonIgnore
    protected String description;

    @Schema(hidden = true)
    @JsonIgnore
    protected String status;

    @Schema(hidden = true)
    @JsonIgnore
    protected String version;

    @Schema(hidden = true)
    @JsonIgnore
    protected List<String> documentation;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Base.class)
    protected String type = TYPE;

    @Schema(hidden = true)
    @JsonIgnore
    protected JsonApiRelationships relationships;

    public ApiCollectionDataRestEntity() {
    }

    public ApiCollectionDataRestEntity(String name, String codeRepository, String description, String status, String version, List<String> documentation, String uri, JsonApiRelationships relationships) {
        this.name = name;
        this.codeRepository = codeRepository;
        this.relationships = relationships;
        this.description = description;
        this.status = status;
        this.version = version;
        this.documentation = documentation;

        this.id = name;
        this.uri = uri;
    }

    @Schema(
            name="API Collection Item Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @Schema(description = "API name", example = "Hello World", required = true)
        @JsonProperty(NAME_KEY)
        protected String name;

        @Schema(description = "URL to the main API code repository",example = "http//github/helloworld", required = false)
        @JsonProperty(CODE_REPOSITORY_KEY)
        protected String codeRepository;

        @Schema(description = "API description", example = "This Hello World API is used to create new worlds", required = true)
        @JsonProperty(DESCRIPTION_KEY)
        protected String description;

        @Schema(description = "Lifecycle status of the API", example = "In production", required = true)
        @JsonProperty(STATUS_KEY)
        protected String status;

        @Schema(description = "Provided API version", example = "1.0.0", required = true)
        @JsonProperty(VERSION_KEY)
        protected String version;

        @Schema(description = "List of URLs where more documentation is to be found.", example = "http://localhost/doc", required = false)
        @JsonProperty(DOCUMENTATION_KEY)
        protected List<String> documentation;

        public Attributes(String name, String codeRepository, String description, String status, String version, List<String> documentation) {
            this.name = name;
            this.codeRepository = codeRepository;
            this.description = description;
            this.status = status;
            this.version = version;
            this.documentation = documentation;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "name='" + name + '\'' +
                    ", codeRepository='" + codeRepository + '\'' +
                    ", description='" + description + '\'' +
                    ", status='" + status + '\'' +
                    ", version='" + version + '\'' +
                    ", documentation=" + documentation +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Base.class)
    public Attributes getAttributes() {
        return new Attributes(
                name,
                codeRepository,
                description,
                status,
                version,
                documentation
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.name = (String) attributes.getOrDefault(NAME_KEY, null);
        this.codeRepository = (String) attributes.getOrDefault(CODE_REPOSITORY_KEY, null);
        this.description = (String) attributes.getOrDefault(DESCRIPTION_KEY, null);
        this.status = (String) attributes.getOrDefault(STATUS_KEY, null);
        this.version = (String) attributes.getOrDefault(VERSION_KEY, null);
        this.documentation = (List<String>) attributes.getOrDefault(DOCUMENTATION_KEY, null);
    }

    @Schema(ref="#/components/schemas/Relationships")
    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public JsonApiRelationships getRelationships() {
        return this.relationships;
    }

    @Schema(ref="#/components/schemas/Links")
    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();

        if(uri != null){ links.put("self", uri); }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<String> getDocumentation() {
        return documentation;
    }

    public void setDocumentation(List<String> documentation) {
        this.documentation = documentation;
    }

    @Override
    public String toString() {
        return "ApiDataRestEntity{" +
                "meta=" + meta +
                ", name='" + name + '\'' +
                ", codeRepository='" + codeRepository + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", version='" + version + '\'' +
                ", documentation=" + documentation +
                ", uri='" + uri + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", relationships=" + relationships +
                '}';
    }
}
