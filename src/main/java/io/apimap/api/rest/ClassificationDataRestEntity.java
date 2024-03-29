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
        name="Classification",
        description = "Connection object between the taxonomy and an API"
)
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class ClassificationDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.CLASSIFICATION_ELEMENT;
    public static final String URN_KEY = "urn";
    public static final String TAXONOMY_VERSION_KEY = "taxonomyVersion";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonIgnore
    @Schema(hidden = true)
    protected String urn;

    @JsonIgnore
    @Schema(hidden = true)
    protected String taxonomyVersion;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    @JsonIgnore
    protected JsonApiRelationships relationships;

    public ClassificationDataRestEntity() {
    }

    public ClassificationDataRestEntity(final String urn,
                                        final String taxonomyVersion) {
        super(urn + "#" + taxonomyVersion);

        this.urn = urn;
        this.taxonomyVersion = taxonomyVersion;
    }

    public ClassificationDataRestEntity(final String urn,
                                        final String taxonomyVersion,
                                        final String uri,
                                        final JsonApiRelationships relationships) {
        super(urn + "#" + taxonomyVersion);

        this.urn = urn;
        this.uri = uri;
        this.relationships = relationships != null ? (JsonApiRelationships) relationships.clone() : null;
        this.taxonomyVersion = taxonomyVersion;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
        this.id = urn + "#" + taxonomyVersion;
    }

    @JsonIgnore
    public String getNid() {
        if (this.urn != null) {
            String[] parts = this.urn.split(":");
            return parts[1];
        }

        return null;
    }

    public String getTaxonomyVersion() {
        return taxonomyVersion;
    }

    public void setTaxonomyVersion(String taxonomyVersion) {
        this.taxonomyVersion = taxonomyVersion;
        this.id = urn + "#" + taxonomyVersion;
    }

    @Schema(
            name="Classification Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes implements Cloneable {
        @JsonProperty(URN_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String urn;

        @JsonProperty(TAXONOMY_VERSION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String taxonomyVersion;

        public Attributes() {
        }

        public Attributes(String urn, String taxonomyVersion) {
            this.urn = urn;
            this.taxonomyVersion = taxonomyVersion;
        }

        @Override
        public Object clone() {
            Attributes returnValue = null;

            try {
                returnValue = (Attributes) super.clone();
            } catch (CloneNotSupportedException e) {
                returnValue = new Attributes();
            }

            returnValue.urn = this.urn;
            returnValue.taxonomyVersion = this.taxonomyVersion;

            return returnValue;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "urn='" + urn + '\'' +
                    ", taxonomyVersion='" + taxonomyVersion + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    public Attributes getAttributes() {
        return new Attributes(
                urn,
                taxonomyVersion
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.urn = (String) attributes.getOrDefault(URN_KEY, null);
        this.taxonomyVersion = (String) attributes.getOrDefault(TAXONOMY_VERSION_KEY, null);
    }

    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public JsonApiRelationships getRelationships() {
        return relationships != null ? (JsonApiRelationships) relationships.clone() : null;
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

    @Override
    public String toString() {
        return "ClassificationDataRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", urn='" + urn + '\'' +
                ", taxonomyVersion='" + taxonomyVersion + '\'' +
                ", uri='" + uri + '\'' +
                ", relationships=" + relationships +
                '}';
    }
}
