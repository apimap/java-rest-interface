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

import java.util.ArrayList;
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
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class ApiCollectionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.API_ELEMENT;
    public static final String NAME_KEY = "name";
    public static final String CODE_REPOSITORY_KEY = "codeRepository";
    public static final String VERSION_KEY = "version";
    public static final String STATUS_KEY = "status";
    public static final String DESCRIPTION_KEY = "description";
    public static final String DOCUMENTATION_KEY = "documentation";

    @Schema(hidden = true)
    @JsonIgnore
    protected ApiDataMetadataEntity meta;

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
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @Schema(hidden = true)
    @JsonIgnore
    protected JsonApiRelationships relationships;

    public ApiCollectionDataRestEntity() {
    }

    public ApiCollectionDataRestEntity(final String name,
                                       final String codeRepository,
                                       final String description,
                                       final String status,
                                       final String version,
                                       final List<String> documentation,
                                       final String uri,
                                       final JsonApiRelationships relationships) {
        super(name);

        this.name = name;
        this.codeRepository = codeRepository;
        this.relationships = relationships != null ? (JsonApiRelationships) relationships.clone() : null;
        this.description = description;
        this.status = status;
        this.version = version;
        this.documentation = new ArrayList<>(documentation);
        this.uri = uri;
    }

    @Schema(
            name="API Collection Item Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes implements Cloneable{
        @Schema(description = "API name", example = "Hello World", required = true)
        @JsonProperty(NAME_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String name;

        @Schema(description = "URL to the main API code repository",example = "http//github/helloworld", required = false)
        @JsonProperty(CODE_REPOSITORY_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String codeRepository;

        @Schema(description = "API description", example = "This Hello World API is used to create new worlds", required = true)
        @JsonProperty(DESCRIPTION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String description;

        @Schema(description = "Lifecycle status of the API", example = "In production", required = true)
        @JsonProperty(STATUS_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String status;

        @Schema(description = "Provided API version", example = "1.0.0", required = true)
        @JsonProperty(VERSION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String version;

        @Schema(description = "List of URLs where more documentation is to be found.", example = "http://localhost/doc", required = false)
        @JsonProperty(DOCUMENTATION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected List<String> documentation;

        public Attributes() {
        }

        public Attributes(final String name,
                          final String codeRepository,
                          final String description,
                          final String status,
                          final String version,
                          final List<String> documentation) {
            this.name = name;
            this.codeRepository = codeRepository;
            this.description = description;
            this.status = status;
            this.version = version;
            this.documentation = new ArrayList<>(documentation);
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
            returnValue.description = this.description;
            returnValue.status = this.status;
            returnValue.version = this.version;
            returnValue.documentation = new ArrayList<>(this.documentation);

            return returnValue;
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
    @JsonView(JsonApiViews.Default.class)
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
        return relationships != null ? (JsonApiRelationships) this.relationships.clone() : null;
    }

    @Schema(ref="#/components/schemas/Links")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();

        if(uri != null){ links.put("self", uri); }

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
        return new ArrayList<>(documentation);
    }

    public void setDocumentation(List<String> documentation) {
        this.documentation = new ArrayList<>(documentation);
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
