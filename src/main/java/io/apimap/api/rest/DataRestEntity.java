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

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import io.apimap.api.rest.jsonapi.JsonApiViews;

@Schema(
        name="Json:api Container Related Object",
        description = "Contains all related entities linked with the content returned inside 'data'"
)
public class DataRestEntity {
    @JsonView(JsonApiViews.Base.class)
    @Schema(description = "Unique Object ID", example = "ID1", required = true)
    protected String id;

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DataRestEntity){
            return ((DataRestEntity) obj).getId().equals(this.id);
        }

        return false;
    }
}
