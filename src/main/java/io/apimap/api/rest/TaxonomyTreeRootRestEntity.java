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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiViews;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxonomyTreeRootRestEntity extends RootRestEntity {

    public static final String VERSION = "1.0.0";

    @JsonValue
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected ArrayList<TaxonomyTreeDataRestEntity> data = new ArrayList<>();

    @JsonView(JsonApiViews.Default.class)
    protected String version;

    public TaxonomyTreeRootRestEntity() {
    }

    @JsonCreator
    public TaxonomyTreeRootRestEntity(ArrayList<TaxonomyTreeDataRestEntity> data) {
        this.data = data;
        this.version = VERSION;
    }

    public TaxonomyTreeRootRestEntity(ArrayList<TaxonomyTreeDataRestEntity> data, String version) {
        this.data = data;
        this.version = version;
    }

    public ArrayList<TaxonomyTreeDataRestEntity> getData() {
        return data;
    }

    public void setData(ArrayList<TaxonomyTreeDataRestEntity> data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TaxonomyCollectionRootRestEntity{" +
                "data=" + data +
                ", version='" + version + '\'' +
                '}';
    }
}
