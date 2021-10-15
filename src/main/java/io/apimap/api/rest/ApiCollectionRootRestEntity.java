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
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiViews;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "data")
public class ApiCollectionRootRestEntity extends RootRestEntity {
    @JsonValue
    @JsonView(JsonApiViews.Base.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected ArrayList<ApiCollectionDataRestEntity> data = new ArrayList<>();

    public ApiCollectionRootRestEntity() {
    }

    @JsonCreator
    public ApiCollectionRootRestEntity(ArrayList<ApiCollectionDataRestEntity> data) {
        this.data = data;
    }

    public ArrayList<ApiCollectionDataRestEntity> getData() {
        return data;
    }

    public void setData(ArrayList<ApiCollectionDataRestEntity> data) {
        this.data = data;
    }

    public void addEntity(ApiCollectionDataRestEntity entity){
        if(this.data == null) this.data = new ArrayList<>();
        this.data.add(entity);
    }

    @Override
    public String toString() {
        return "ApiCollectionRootRestEntity{" +
                "data=" + data +
                '}';
    }
}
