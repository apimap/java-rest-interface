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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="API Version",
        description = "Core version entity used to describe an API version"
)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "data")
public class ApiVersionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRootObject.VERSION_ELEMENT;

    public static final String VERSION_KEY = "version";
    public static final String CREATED_KEY = "created";

    @Schema(hidden = true)
    @JsonIgnore
    protected String version;

    @Schema(hidden = true)
    @JsonIgnore
    protected Date created;

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Base.class)
    protected String type = TYPE;

    @JsonIgnore
    protected String uri;

    public ApiVersionDataRestEntity() {
    }

    public ApiVersionDataRestEntity(String version) {
        this.version = version;
    }

    public ApiVersionDataRestEntity(String version, Date created, String uri) {
        this.version = version;
        this.created = created;
        this.uri = uri;
        this.id = version;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
        this.id = version;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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

    @Schema(
            name="API Version Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @JsonProperty(VERSION_KEY)
        @Schema(description = "URL to the main API code repository",example = "http//github/helloworld", required = false)
        protected String version;

        @JsonView(JsonApiViews.Complete.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        @JsonProperty(CREATED_KEY)
        @Schema(description = "URL to the main API code repository", example = "http//github/helloworld", required = false)
        protected Date created;

        public Attributes(String version, Date created) {
            this.version = version;
            this.created = created;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "version='" + version + '\'' +
                    ", created=" + created +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Base.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Attributes getAttributes() {
        return new Attributes(
                version,
                created
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.version = (String) attributes.getOrDefault("version", null);
    }

    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();

        if(uri != null){ links.put("self", uri); }

        if(links.size() < 1) return null;
        return links;
    }

    @Override
    public String toString() {
        return "ApiVersionDataRestEntity{" +
                "version='" + version + '\'' +
                ", created=" + created +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
