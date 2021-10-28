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
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
public class TaxonomyTreeDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.TAXONOMY_ELEMENT;
    public static final String URN_KEY = "urn";
    public static final String URL_KEY = "url";
    public static final String TITLE_KEY = "title";
    public static final String DESCRIPTION_KEY = "description";
    public static final String ENTITIES_KEY = "entities";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonProperty(URN_KEY)
    @Schema(hidden = true)
    protected String urn;

    @JsonProperty(URL_KEY)
    @Schema(hidden = true)
    protected String url;

    @JsonProperty(TITLE_KEY)
    @Schema(hidden = true)
    protected String title;

    @JsonProperty(DESCRIPTION_KEY)
    @Schema(hidden = true)
    protected String description;

    @JsonProperty(ENTITIES_KEY)
    @Schema(hidden = true)
    protected ArrayList<TaxonomyTreeDataRestEntity> entities = new ArrayList<>();

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    public TaxonomyTreeDataRestEntity() {
    }

    public TaxonomyTreeDataRestEntity(String urn, String url, String title, String description) {
        this.urn = urn;
        this.url = url;
        this.title = title;
        this.description = description;
        this.id = urn;
    }

    public TaxonomyTreeDataRestEntity(String urn, String url, String title, String description, String uri) {
        this.urn = urn;
        this.url = url;
        this.title = title;
        this.description = description;
        this.id = urn;
        this.uri = uri;
    }

    public TaxonomyTreeDataRestEntity(String urn, String url, String title, String description, String uri, ArrayList<TaxonomyTreeDataRestEntity> entities) {
        this.urn = urn;
        this.url = url;
        this.title = title;
        this.description = description;
        this.entities = entities;
        this.id = urn;
        this.uri = uri;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<TaxonomyTreeDataRestEntity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<TaxonomyTreeDataRestEntity> entities) {
        this.entities = entities;
    }

    @Schema(
            name="Taxonomy Tree Item Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @JsonProperty(URN_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String urn;

        @JsonProperty(URL_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String url;

        @JsonProperty(TITLE_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String title;

        @JsonProperty(DESCRIPTION_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String description;

        @JsonProperty(ENTITIES_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected ArrayList<TaxonomyTreeDataRestEntity> entities = new ArrayList<>();

        public Attributes(String urn, String url, String title, String description, ArrayList<TaxonomyTreeDataRestEntity> entities) {
            this.urn = urn;
            this.url = url;
            this.title = title;
            this.description = description;
            this.entities = entities;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "urn='" + urn + '\'' +
                    ", url='" + url + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", entities=" + entities +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    public Attributes getAttributes() {
        return new Attributes(
                urn,
                url,
                title,
                description,
                entities
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.urn = (String) attributes.getOrDefault("urn", null);
        this.url = (String) attributes.getOrDefault("url", null);
        this.title = (String) attributes.getOrDefault("title", null);
        this.description = (String) attributes.getOrDefault("description", null);
        this.uri = (String) attributes.getOrDefault("uri", null);
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
        return "TaxonomyCollectionDataRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", urn='" + urn + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", entities=" + entities +
                ", uri='" + uri + '\'' +
                '}';
    }
}