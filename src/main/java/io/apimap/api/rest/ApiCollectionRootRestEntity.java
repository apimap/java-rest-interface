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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.rest.RootRestEntity;
import io.apimap.rest.jsonapi.JsonApiViews;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiCollectionRootRestEntity extends RootRestEntity {
    @JsonValue
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<ApiCollectionDataRestEntity> data = new ArrayList<>();

    public ApiCollectionRootRestEntity() {
    }

    @JsonCreator
    public ApiCollectionRootRestEntity(List<ApiCollectionDataRestEntity> data) {
        this.data = new ArrayList<>(data);
    }

    public List<ApiCollectionDataRestEntity> getData() {
        return new ArrayList<>(data);
    }

    public void setData(List<ApiCollectionDataRestEntity> data) {
        this.data = new ArrayList<>(data);
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
