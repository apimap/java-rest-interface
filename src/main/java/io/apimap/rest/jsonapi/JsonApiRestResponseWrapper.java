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

package io.apimap.rest.jsonapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.DataRestEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Schema(
        name="Json:api Container Object",
        description = "This object is the container object used in all responses from the API."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonApiRestResponseWrapper<T> {
    public static final String API_COLLECTION = "api:collection";
    public static final String API_ELEMENT = "api:element";
    public static final String API_META_ELEMENT = "apimetadata:element";
    public static final String TAXONOMY_COLLECTION = "taxonomy:collection";
    public static final String TAXONOMY_ELEMENT = "taxonomy:element";
    public static final String CLASSIFICATION_COLLECTION = "classification:collection";
    public static final String CLASSIFICATION_ELEMENT = "classification:element";
    public static final String CLASSIFICATION_TREE = "classification:tree";
    public static final String VERSION_COLLECTION = "version:collection";
    public static final String VERSION_ELEMENT = "version:element";
    public static final String METADATA_COLLECTION = "metadata:collection";
    public static final String METADATA_CONTAINER = "metadata:container";
    public static final String METADATA_ELEMENT = "metadata:element";
    public static final String URN_COLLECTION = "urn:collection";
    public static final String URN_ELEMENT = "urn:element";
    public static final String STATISTICS_COLLECTION = "statistics:collection";
    public static final String STATISTICS_ELEMENT = "statistics:element";
    public static final String STATISTICS_ENTRY = "statistics:entry";
    public static final String MESH_COLLECTION = "mesh:collection";
    public static final String MESH_ELEMENT = "mesh:element";
    public static final String README_ELEMENT = "readme:element";
    public static final String CHANGELOG_ELEMENT = "changelog:element";
    public static final String VOTE_COLLECTION = "vote:collection";
    public static final String VOTE_ELEMENT = "vote:element";
    public static final String ACCESS_TOKEN_ELEMENT = "access-token:element";
    public static final String ACCESS_TOKEN_COLLECTION = "access-token:collection";
    public static final String ACCESS_TOKEN_VALIDITY_ELEMENT = "access-token-validity:element";
    public static final String ORGANIZATION_COLLECTION = "organization:collection";
    public static final String ORGANIZATION_ELEMENT = "organization:element";
    public static final String ACCOUNT_COLLECTION = "account:collection";
    public static final String ACCOUNT_ELEMENT = "account:element";

    @Schema(description = "Resource/collection main object")
    @JsonView(JsonApiViews.Default.class)
    protected T data;

    @Schema(description = "Urls connecting this resource/collection with other related resources/collections")
    @JsonView(JsonApiViews.Default.class)
    protected HashMap<String, Object> links = new HashMap<>();

    @Schema(description = "Human readable metadata")
    @JsonView(JsonApiViews.Default.class)
    protected HashMap<String, String> meta = new HashMap<>();

    @Schema(description = "Generic JSON:API implementation details")
    @JsonView(JsonApiViews.Default.class)
    protected HashMap<String, String> jsonapi = new HashMap<>();

    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "Contains all related entities linked with the content returned inside 'data'")
    protected ArrayList<DataRestEntity> included = new ArrayList();

    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "Contains all errors in the request")
    protected ArrayList<JsonApiError> errors = null;

    public JsonApiRestResponseWrapper() {
        this.jsonapi.put("version", "1.1");
    }

    public JsonApiRestResponseWrapper(T data) {
        this.data = data;
        this.jsonapi.put("version", "1.1");
    }

    public JsonApiRestResponseWrapper(T data, HashMap<String, String> meta) {
        this.data = data;
        this.meta = new HashMap<>(meta);
        this.jsonapi.put("version", "1.1");
    }

    public Map<String, String> getMeta() {
        return new HashMap<>(meta);
    }

    public void addMetadata(String key, String value) {
        this.meta.put(key, value);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLinks(HashMap<String, Object> links) {
        this.links = new HashMap<>(links);
    }

    public void setMeta(HashMap<String, String> meta) {
        this.meta = new HashMap<>(meta);
    }

    public void setJsonapi(HashMap<String, String> jsonapi) {
        this.jsonapi = new HashMap<>(jsonapi);
    }

    public void setIncluded(ArrayList<DataRestEntity> included) {
        this.included = new ArrayList<>(included);
    }

    public HashMap<String, Object> getLinks() {
        return new HashMap<>(links);
    }

    public void addIncluded(DataRestEntity object){
        this.included.add(object);
    }

    public ArrayList<DataRestEntity> getIncluded() {
        return included != null ? new ArrayList<>(included) : null;
    }

    public ArrayList<JsonApiError> getErrors() {
        return errors != null ? new ArrayList<>(errors) : null;
    }

    public void setErrors(ArrayList<JsonApiError> errors) {
        this.errors = new ArrayList<>(errors);
    }

    public void addError(JsonApiError error){
        if(this.errors == null) this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    @Schema(hidden = true)
    public void setSelf(URI uri) {
        addLink("self", uri.toString());
    }

    public void addRelatedRef(String rel, URI uri) {
        ArrayList related = (ArrayList) this.links.getOrDefault("related", new ArrayList<>());

        HashMap item = new HashMap();
        item.put("href", uri.toString());
        item.put("rel", rel);

        related.add(item);

        this.links.put("related", related);
    }

    public void addIncludedObject(DataRestEntity object){
        if(!this.included.contains(object)){
            this.included.add(object);
        }
    }

    protected void addLink(String key, Object value) {
        this.links.put(key, value);
    }

    public HashMap<String, String> getJsonapi() {
        return new HashMap<>(jsonapi);
    }

    public void appendDuration(long start, long end) {
        if (this.meta != null) {
            addMetadata("millis", String.valueOf(end - start));
        }
    }

    @Override
    public String toString() {
        return "JsonApiRestResponseWrapper{" +
                "data=" + data +
                ", links=" + links +
                ", meta=" + meta +
                ", jsonapi=" + jsonapi +
                ", included=" + included +
                ", errors=" + errors +
                '}';
    }
}
