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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
public class TaxonomyTreeDataRestEntity extends TaxonomyDataRestEntity {

    public static final String ENTITIES_KEY = "entities";

    @Schema(description = "Object type definition", defaultValue = JsonApiRestResponseWrapper.URN_ELEMENT, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = JsonApiRestResponseWrapper.URN_ELEMENT;

    @JsonProperty(ENTITIES_KEY)
    @Schema(hidden = true)
    protected ArrayList<TaxonomyTreeDataRestEntity> entities = new ArrayList<>();

    public TaxonomyTreeDataRestEntity() {
    }

    public TaxonomyTreeDataRestEntity(ArrayList<TaxonomyTreeDataRestEntity> entities) {
        this.entities = entities;
    }

    public TaxonomyTreeDataRestEntity(String urn, String title, String url, String description, String uri, String taxonomyVersion, ReferenceType referenceType, ArrayList<TaxonomyTreeDataRestEntity> entities) {
        super(urn, title, url, description, uri, taxonomyVersion, referenceType);
        this.entities = entities;
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
    public static class Attributes extends TaxonomyDataRestEntity.Attributes {
        @JsonProperty(ENTITIES_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected ArrayList<TaxonomyTreeDataRestEntity> entities = new ArrayList<>();

        public Attributes(String urn, String title, String url, String description, ReferenceType referenceType, ArrayList<TaxonomyTreeDataRestEntity> entities) {
            super(urn, title, url, description, referenceType);
            this.entities = entities;
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
                referenceType,
                entities
        );
    }

    @Override
    public String toString() {
        return "TaxonomyTreeDataRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", urn='" + urn + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", referenceType=" + referenceType +
                ", taxonomyVersion='" + taxonomyVersion + '\'' +
                ", uri='" + uri + '\'' +
                ", entities=" + entities +
                '}';
    }
}