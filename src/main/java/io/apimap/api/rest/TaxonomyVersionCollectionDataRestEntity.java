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
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Taxonomy Version",
        description = "Entity used to return lists of taxonomy versions"
)
public class TaxonomyVersionCollectionDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRootObject.VERSION_ELEMENT;
    public static final String VERSION_KEY = "version";
    public static final String NID_KEY = "nid";

    @JsonProperty(VERSION_KEY)
    @Schema(hidden = true)
    protected String version;

    @JsonProperty(NID_KEY)
    @Schema(hidden = true)
    protected String nid;

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Base.class)
    protected String type = TYPE;

    @Schema(hidden = true)
    @JsonIgnore
    protected String uri;

    public TaxonomyVersionCollectionDataRestEntity() {
    }

    public TaxonomyVersionCollectionDataRestEntity(String version, String nid) {
        this.version = version;
        this.nid = nid;
        this.id = nid + "#" + version;
    }

    public TaxonomyVersionCollectionDataRestEntity(String version, String nid, String uri) {
        this.version = version;
        this.nid = nid;
        this.uri = uri;
        this.id = nid + "#" + version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
        this.id = version;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Schema(
            name="Taxonomy Version Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @JsonProperty(VERSION_KEY)
        protected String version;

        @JsonProperty(NID_KEY)
        protected String nid;

        public Attributes(String version, String nid) {
            this.version = version;
            this.nid = nid;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "version='" + version + '\'' +
                    ", nid='" + nid + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Base.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Attributes getAttributes() {
        return new Attributes(
                version,
                nid
        );
    }

    @Schema(ref="#/components/schemas/links")
    @JsonProperty
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();
        links.put("self", uri);

        return links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxonomyVersionCollectionDataRestEntity that = (TaxonomyVersionCollectionDataRestEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TaxonomyVersionCollectionDataRestEntity{" +
                "version='" + version + '\'' +
                ", taxonomy='" + nid + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
