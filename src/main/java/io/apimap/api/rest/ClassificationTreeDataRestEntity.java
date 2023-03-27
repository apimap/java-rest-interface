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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Classification Tree Item Attributes",
        description = "Classification tree item used to list the taxonomy relations"
)
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class ClassificationTreeDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.CLASSIFICATION_TREE;
    public static final String PATH_KEY = "path";
    public static final String URN_KEY = "urn";
    public static final String TITLE_KEY = "title";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonProperty(PATH_KEY)
    @Schema(hidden = true)
    protected ArrayList<String> path;

    @JsonProperty(URN_KEY)
    @Schema(hidden = true)
    protected String urn;

    @JsonProperty(TITLE_KEY)
    @Schema(hidden = true)
    protected String title;

    @JsonIgnore
    protected String uri;

    @JsonIgnore
    protected JsonApiRelationships relationships;

    public ClassificationTreeDataRestEntity() {
    }

    public ClassificationTreeDataRestEntity(final String urn,
                                            final String title,
                                            final ArrayList<String> path) {
        super(urn);

        this.urn = urn;
        this.title = title;
        this.path = new ArrayList<>(path);
    }

    public ClassificationTreeDataRestEntity(final String urn,
                                            final String title,
                                            final String uri,
                                            final ArrayList<String> path,
                                            final JsonApiRelationships relationships) {
        super(urn);

        this.urn = urn;
        this.title = title;
        this.path = new ArrayList<>(path);
        this.uri = uri;
        this.relationships = relationships != null ? (JsonApiRelationships) relationships.clone() : null;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getPath() {
        return new ArrayList<>(path);
    }

    @Schema(
            name="Classification Tree Item Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @JsonProperty(PATH_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected ArrayList<String> path;

        @JsonProperty(URN_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String urn;

        @JsonProperty(TITLE_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String title;

        public Attributes(ArrayList<String> path, String urn, String title) {
            this.path = new ArrayList<>(path);
            this.urn = urn;
            this.title = title;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "path=" + path +
                    ", urn='" + urn + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    public Attributes getAttributes() {
        return new Attributes(
                path,
                urn,
                title
        );
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
        links.put("self", uri);

        return links;
    }

    @Override
    public String toString() {
        return "ClassificationTreeDataRestEntity{" +
                "type='" + type + '\'' +
                ", path=" + path +
                ", urn='" + urn + '\'' +
                ", title='" + title + '\'' +
                ", uri='" + uri + '\'' +
                ", relationships=" + relationships +
                ", id='" + id + '\'' +
                '}';
    }
}
