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

package io.apimap.api.rest.jsonapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

@Schema(hidden = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonApiRelationships {

    @JsonValue
    @JsonView(JsonApiViews.Default.class)
    protected HashMap<String, Link> relationships = new HashMap<>();

    public JsonApiRelationships() {
    }

    public void addRelationshipRef(String key, URI uri) {
        Link link = relationships.get(key);

        if(link == null){
            link = new Link();
            relationships.put(key, link);
        }

        link.setSelf(uri);
    }

    public void addDataRef(String key, String type, String id){
        Link link = relationships.get(key);

        if(link == null){
            link = new Link();
            relationships.put(key, link);
        }

        link.addDataRef(type, id);
    }

    @JsonValue
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, Link> getRelationships() {
        return relationships;
    }

    public void setRelationships(HashMap<String, Link> relationships) {
        this.relationships = relationships;
    }

    @Override
    public String toString() {
        return "JsonApiRelationships{" +
                "relationships=" + relationships +
                '}';
    }

    @Schema(hidden = true)
    public class Link {
        @JsonView(JsonApiViews.Default.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        protected HashMap<String, String> links = new HashMap<>();

        @JsonView(JsonApiViews.Default.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        protected ArrayList<HashMap<String, String>> data = new ArrayList();

        public void setSelf(URI uri) {
            links.put("self", uri.toString());
        }

        public HashMap<String, String> getLinks() {
            return links;
        }

        public void addDataRef(String type, String id){
            for (HashMap<String, String> item : data) {
                if(item.get("type").equals(type) && item.get("id").equals(id)){
                    return;
                }
            }

            HashMap item = new HashMap();
            item.put("type", type);
            item.put("id", id);

            data.add(item);
        }

        public ArrayList<HashMap<String, String>> getData() {
            return data;
        }

        public void setLinks(HashMap<String, String> links) {
            this.links = links;
        }

        public void setData(ArrayList<HashMap<String, String>> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "links=" + links +
                    ", data=" + data +
                    '}';
        }
    }
}