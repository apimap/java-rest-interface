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
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class ApiVersionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.VERSION_ELEMENT;

    public static final String VERSION_KEY = "version";
    public static final String CREATED_KEY = "created";
    public static final String RATING_KEY = "rating";

    @Schema(hidden = true)
    @JsonIgnore
    protected String version;

    @Schema(hidden = true)
    @JsonIgnore
    protected Date created;

    @Schema(hidden = true)
    @JsonIgnore
    protected ApiVersionRatingEntity rating;

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonIgnore
    protected String uri;

    public ApiVersionDataRestEntity() {
    }

    public ApiVersionDataRestEntity(final String version) {
        super(version);

        this.version = version;
    }

    public ApiVersionDataRestEntity(final String version,
                                    final Date created,
                                    final String uri) {
        super(version);

        this.version = version;
        this.created = created != null ? (Date)created.clone() : null;
        this.uri = uri;
    }

    public ApiVersionDataRestEntity(final String version,
                                    final Date created,
                                    final ApiVersionRatingEntity rating,
                                    final String uri) {
        super(version);

        this.version = version;
        this.created = created != null ? (Date)created.clone() : null;
        this.rating = rating != null ? (ApiVersionRatingEntity) rating.clone() : null;
        this.uri = uri;
    }

    @Override
    public Object clone() {
        ApiVersionDataRestEntity returnValue = (ApiVersionDataRestEntity) super.clone();

        returnValue.version = this.version;
        returnValue.created = this.created;
        returnValue.rating = rating != null ? (ApiVersionRatingEntity) this.rating.clone() : null;
        returnValue.uri = this.uri;

        return returnValue;
    }

    public Date getCreated() {
        return created != null ? (Date)created.clone() : null;
    }

    public void setCreated(Date created) {
        this.created = created != null ? (Date)created.clone() : null;
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
    public static class Attributes implements Cloneable{
        @JsonProperty(VERSION_KEY)
        @Schema(description = "URL to the main API code repository",example = "http//github/helloworld", required = false)
        @JsonView(JsonApiViews.Default.class)
        protected String version;

        @JsonView(JsonApiViews.Default.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        @JsonProperty(CREATED_KEY)
        @Schema(description = "Date created", required = false)
        protected Date created;

        @JsonView(JsonApiViews.Default.class)
        @JsonProperty(RATING_KEY)
        @Schema(description = "Rating", required = false)
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        protected ApiVersionRatingEntity rating;

        public Attributes() {
        }

        public Attributes(final String version,
                          final Date created) {
            this.version = version;
            this.created = created != null ? (Date)created.clone() : null;
        }

        public Attributes(final String version,
                          final Date created,
                          final ApiVersionRatingEntity rating) {
            this.version = version;
            this.created = created != null ? (Date)created.clone() : null;
            this.rating = rating != null ? (ApiVersionRatingEntity) rating.clone() : null;
        }

        @Override
        public Object clone() {
            Attributes returnValue = null;

            try {
                returnValue = (Attributes) super.clone();
            } catch (CloneNotSupportedException e) {
                returnValue = new Attributes();
            }

            returnValue.version = this.version;
            returnValue.created = this.created;
            returnValue.rating = this.rating != null ? (ApiVersionRatingEntity)this.rating.clone() : null;

            return returnValue;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "version='" + version + '\'' +
                    ", created=" + created +
                    ", rating=" + rating +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Attributes getAttributes() {
        return new Attributes(
                version,
                created,
                rating
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.version = (String) attributes.getOrDefault(VERSION_KEY, null);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
                ", rating=" + rating +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
