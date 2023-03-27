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
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.DataRestEntity;

import java.util.ArrayList;

public class JsonApiIncluded {
    protected ArrayList<DataRestEntity> included = new ArrayList();

    public JsonApiIncluded() {
    }

    public JsonApiIncluded(ArrayList<DataRestEntity> included) {
        this.included = new ArrayList<>(included);
    }

    public void addIncluded(DataRestEntity object) {
        included.add(object);
    }

    @JsonValue
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ArrayList<DataRestEntity> getIncluded() {
        return new ArrayList<>(included);
    }

    @Override
    public String toString() {
        return "JsonApiIncluded{" +
                "included=" + included +
                '}';
    }
}
