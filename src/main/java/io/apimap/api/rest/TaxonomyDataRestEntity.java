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
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.ref.Reference;
import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Taxonomy",
        description = "Core taxonomy entity used to describe an taxonomy entity"
)
public class TaxonomyDataRestEntity extends DataRestEntity {
    public final String TYPE = JsonApiRestResponseWrapper.TAXONOMY_ELEMENT;
    public static final String URN_KEY = "urn";
    public static final String TITLE_KEY = "title";
    public static final String URL_KEY = "url";
    public static final String URI_KEY = "uri";
    public static final String DESCRIPTION_KEY = "description";
    public static final String TYPE_KEY = "type";

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum ReferenceType {
        @JsonProperty("classification")
        CLASSIFICATION("classification"),
        @JsonProperty("reference")
        REFERENCE("reference"),
        @JsonProperty("unknown")
        UNKNOWN("unknown");

        public final String value;

        @JsonCreator
        ReferenceType(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "ReferenceType{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonIgnore
    @Schema(hidden = true)
    protected String urn;

    @JsonIgnore
    @Schema(hidden = true)
    protected String title;

    @JsonIgnore
    @Schema(hidden = true)
    protected String url;

    @JsonIgnore
    @Schema(hidden = true)
    protected String description;

    @JsonIgnore
    @Schema(hidden = true)
    protected ReferenceType referenceType;

    @Schema(hidden = true)
    @JsonView(JsonApiViews.Default.class)
    protected String taxonomyVersion;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    public TaxonomyDataRestEntity() {
    }

    public TaxonomyDataRestEntity(String urn, String title, String url, String description, String uri, String taxonomyVersion, ReferenceType referenceType) {
        this.urn = urn;
        this.title = title;
        this.url = url;
        this.description = description;
        this.id = urn;
        this.uri = uri;
        this.taxonomyVersion = taxonomyVersion;
        this.referenceType = referenceType;
    }

    public String getTaxonomyVersion() {
        return taxonomyVersion;
    }

    public void setTaxonomyVersion(String taxonomyVersion) {
        this.taxonomyVersion = taxonomyVersion;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
        this.id = urn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReferenceType getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    @Schema(
            name="Taxonomy Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @JsonProperty(URN_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String urn;

        @JsonProperty(TITLE_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String title;

        @JsonProperty(URL_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String url;

        @JsonProperty(DESCRIPTION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String description;

        @JsonProperty(TYPE_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected ReferenceType referenceType;

        public Attributes(String urn, String title, String url, String description, ReferenceType referenceType) {
            this.urn = urn;
            this.title = title;
            this.url = url;
            this.description = description;
            this.referenceType = referenceType;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "urn='" + urn + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", description='" + description + '\'' +
                    ", referenceType=" + referenceType +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    public Attributes getAttributes() {
        return new Attributes(
                urn,
                title,
                url,
                description,
                referenceType
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.urn = (String) attributes.getOrDefault(URN_KEY, null);
        this.url = (String) attributes.getOrDefault(URL_KEY, null);
        this.title = (String) attributes.getOrDefault(TITLE_KEY, null);
        this.description = (String) attributes.getOrDefault(DESCRIPTION_KEY, null);
        this.uri = (String) attributes.getOrDefault(URI_KEY, null);

        String type = ((String)attributes.getOrDefault(TYPE_KEY, (Object)null));
        this.referenceType = ReferenceType.valueOf(type != null ? type.toUpperCase() : null);
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
        return "TaxonomyDataRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", urn='" + urn + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", referenceType=" + referenceType +
                ", taxonomyVersion='" + taxonomyVersion + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
