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

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class TaxonomyTreeDataRestEntity extends TaxonomyDataRestEntity {

    public static final String ENTITIES_KEY = "entities";

    @SuppressFBWarnings()
    @Schema(description = "Object type definition", defaultValue = JsonApiRestResponseWrapper.URN_ELEMENT, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = JsonApiRestResponseWrapper.URN_ELEMENT;

    @Schema(hidden = true)
    @JsonIgnore
    protected ArrayList<TaxonomyTreeDataRestEntity> entities = new ArrayList<>();

    public TaxonomyTreeDataRestEntity() {
    }

    public TaxonomyTreeDataRestEntity(ArrayList<TaxonomyTreeDataRestEntity> entities) {
        this.entities = new ArrayList<>(entities);
    }

    public TaxonomyTreeDataRestEntity(final String urn,
                                      final String title,
                                      final String url,
                                      final String description,
                                      final String uri,
                                      final String taxonomyVersion,
                                      final ReferenceType referenceType,
                                      final ArrayList<TaxonomyTreeDataRestEntity> entities) {
        super(urn, title, url, description, uri, taxonomyVersion, referenceType);

        if(entities != null)
            this.entities = new ArrayList<>(entities);
        else
            this.entities = new ArrayList<>();
    }

    public ArrayList<TaxonomyTreeDataRestEntity> getEntities() {
        return new ArrayList<>(entities);
    }

    public void setEntities(ArrayList<TaxonomyTreeDataRestEntity> entities) {
        this.entities = new ArrayList<>(entities);
    }

    @Schema(
            name="Taxonomy Tree Item Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes extends TaxonomyDataRestEntity.Attributes{
        @JsonProperty(ENTITIES_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected ArrayList<TaxonomyTreeDataRestEntity> entities = new ArrayList<>();

        public Attributes() {
            super();
        }

        public Attributes(final String urn,
                          final String title,
                          final String url,
                          final String description,
                          final ReferenceType referenceType,
                          final ArrayList<TaxonomyTreeDataRestEntity> entities) {
            super(urn, title, url, description, referenceType);
            this.entities = new ArrayList<>(entities);
        }

        @Override
        public Object clone() {
            Attributes returnValue =  (Attributes) super.clone();

            returnValue.entities = new ArrayList<>(this.entities);

            return returnValue;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                "entities=" + entities +
                ", urn='" + urn + '\'' +
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