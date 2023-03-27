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
        name="Taxonomy Collection",
        description = "Core taxonomy collection entity used to describe a taxonomy collection"
)
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class TaxonomyCollectionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.TAXONOMY_ELEMENT;
    public static final String NAME_KEY = "name";
    public static final String NID_KEY = "nid";
    public static final String DESCRIPTION_KEY = "description";
    public static final String TOKEN_KEY = "token";
    public static final String META_KEY = "meta";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @Schema(hidden = true)
    @JsonIgnore
    protected String name;

    @Schema(hidden = true)
    @JsonIgnore
    protected String nid;

    @Schema(hidden = true)
    @JsonIgnore
    protected String description;

    @Schema(hidden = true)
    @JsonProperty(META_KEY)
    @JsonView(JsonApiViews.Extended.class)
    protected ApiDataMetadataEntity meta;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    @Schema(hidden = true)
    protected JsonApiRelationships relationships;

    public TaxonomyCollectionDataRestEntity() {
    }

    public TaxonomyCollectionDataRestEntity(final String name,
                                            final String description,
                                            final String nid) {
        super(nid);

        this.name = name;
        this.nid = nid;
        this.description = description;
    }

    public TaxonomyCollectionDataRestEntity(final String name,
                                            final String description,
                                            final String nid,
                                            final String uri,
                                            final JsonApiRelationships relationships) {
        super(nid);

        this.name = name;
        this.nid = nid;
        this.uri = uri;
        this.description = description;
        this.relationships = relationships != null ? (JsonApiRelationships) relationships.clone() : null;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }

    public ApiDataMetadataEntity getMeta() {
        return meta != null ? (ApiDataMetadataEntity) meta.clone() : null;
    }

    public void setMeta(ApiDataMetadataEntity meta) {
        this.meta = (meta != null) ? (ApiDataMetadataEntity) meta.clone() : null;
    }

    @Schema(
            name="Taxonomy Collection Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Attributes implements Cloneable{
        @JsonProperty(NAME_KEY)
        @Schema(description = "Taxonomy name",example = "My First Taxonomy", required = true)
        @JsonView(JsonApiViews.Default.class)
        protected String name;

        @JsonProperty(NID_KEY)
        @Schema(description = "NID identifier (alphanum) 0*30(ldh) (alphanum)",example = "apimap", required = true)
        @JsonView(JsonApiViews.Default.class)
        protected String nid;

        @JsonProperty(DESCRIPTION_KEY)
        @Schema(description = "Short taxonomy description",example = "This taxonomy is used to classify My Organization APIs", required = true)
        @JsonView(JsonApiViews.Default.class)
        protected String description;

        public Attributes() {
        }

        public Attributes(String name, String nid, String description) {
            this.name = name;
            this.nid = nid;
            this.description = description;
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
            returnValue.nid = this.nid;
            returnValue.description = this.description;

            return returnValue;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "name='" + name + '\'' +
                    ", nid='" + nid + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    public Attributes getAttributes() {
        return new Attributes(
                name,
                nid,
                description
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.name = (String) attributes.getOrDefault(NAME_KEY, null);
        this.nid = (String) attributes.getOrDefault(NID_KEY, null);
        this.description = (String) attributes.getOrDefault(DESCRIPTION_KEY, null);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();
        links.put("self", uri);

        return links;
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

    @Override
    public String toString() {
        return "TaxonomyCollectionDataRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", nid='" + nid + '\'' +
                ", description='" + description + '\'' +
                ", meta=" + meta +
                ", uri='" + uri + '\'' +
                ", relationships=" + relationships +
                '}';
    }
}
