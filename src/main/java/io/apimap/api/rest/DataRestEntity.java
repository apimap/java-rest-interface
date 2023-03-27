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

import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(
        name="Json:api Container Related Object",
        description = "Contains all related entities linked with the content returned inside 'data'"
)
public class DataRestEntity implements Cloneable{
    @JsonView(JsonApiViews.Default.class)
    @Schema(description = "Unique Object ID", example = "ID1", required = true)
    protected String id;

    public DataRestEntity() {
    }

    public DataRestEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Object clone() {
        DataRestEntity returnValue = null;

        try {
            returnValue = (DataRestEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            returnValue = new DataRestEntity();
        }

        returnValue.id = this.id;

        return returnValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DataRestEntity){
            return ((DataRestEntity) obj).getId().equals(this.id);
        }

        return false;
    }
}
