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
import io.swagger.v3.oas.annotations.media.Schema;
import io.apimap.api.rest.jsonapi.JsonApiRelationships;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import io.apimap.api.rest.jsonapi.JsonApiViews;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Taxonomy Collection",
        description = "Core taxonomy collection entity used to describe a taxonomy collection"
)
public class TaxonomyCollectionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRootObject.TAXONOMY_ELEMENT;
    public static final String NAME_KEY = "name";
    public static final String NID_KEY = "nid";
    public static final String DESCRIPTION_KEY = "description";
    public static final String TOKEN_KEY = "token";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Base.class)
    protected String type = TYPE;

    @JsonProperty(NAME_KEY)
    @Schema(hidden = true)
    protected String name;

    @JsonProperty(NID_KEY)
    @Schema(hidden = true)
    protected String nid;

    @JsonProperty(DESCRIPTION_KEY)
    @Schema(hidden = true)
    protected String description;

    @JsonProperty(TOKEN_KEY)
    @Schema(hidden = true)
    @JsonView(JsonApiViews.Hidden.class)
    protected String token;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    @JsonIgnore
    protected JsonApiRelationships relationships;

    public TaxonomyCollectionDataRestEntity() {
    }

    public TaxonomyCollectionDataRestEntity(String name, String description, String nid) {
        this.name = name;
        this.nid = nid;
        this.description = description;
        this.id = nid;
    }

    public TaxonomyCollectionDataRestEntity(String name, String description, String nid, String token) {
        this.name = name;
        this.nid = nid;
        this.description = description;
        this.id = nid;
        this.token = token;
    }

    public TaxonomyCollectionDataRestEntity(String name, String description, String nid, String uri, String token, JsonApiRelationships relationships) {
        this.name = name;
        this.nid = nid;
        this.uri = uri;
        this.id = nid;
        this.token = token;
        this.description = description;
        this.relationships = relationships;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Schema(
            name="Taxonomy Collection Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Attributes {
        @JsonProperty(NAME_KEY)
        protected String name;

        @JsonProperty(NID_KEY)
        protected String nid;

        @JsonProperty(DESCRIPTION_KEY)
        protected String description;

        @JsonProperty(TOKEN_KEY)
        @Schema(hidden = true)
        @JsonView(JsonApiViews.Hidden.class)
        protected String token;

        public Attributes(String name, String nid, String description, String token) {
            this.name = name;
            this.nid = nid;
            this.description = description;
            this.token = token;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "name='" + name + '\'' +
                    ", nid='" + nid + '\'' +
                    ", description='" + description + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Base.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Attributes getAttributes() {
        return new Attributes(
                name,
                nid,
                description,
                token
        );
    }

    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();
        links.put("self", uri);

        return links;
    }

    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public JsonApiRelationships getRelationships() {
        return this.relationships;
    }

    @Override
    public String toString() {
        return "TaxonomyCollectionDataRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", nid='" + nid + '\'' +
                ", description='" + description + '\'' +
                ", token='" + token + '\'' +
                ", uri='" + uri + '\'' +
                ", relationships=" + relationships +
                '}';
    }
}
