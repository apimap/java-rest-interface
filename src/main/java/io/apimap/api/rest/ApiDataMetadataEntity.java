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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.rest.jsonapi.JsonApiViews;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
public class ApiDataMetadataEntity implements Cloneable {
    public static final String TYPE = JsonApiRestResponseWrapper.API_META_ELEMENT;
    public static final String TOKEN_KEY = "token";

    @JsonView(JsonApiViews.Default.class)
    @JsonProperty(TOKEN_KEY)
    protected String token;

    public ApiDataMetadataEntity() {
    }

    public ApiDataMetadataEntity(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object clone() {
        ApiDataMetadataEntity returnValue = null;

        try {
            returnValue = (ApiDataMetadataEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            returnValue = new ApiDataMetadataEntity();
        }

        returnValue.token = this.token;

        return returnValue;
    }

    @Override
    public String toString() {
        return "ApiDataApiMetadataEntity{" +
                ", token='" + token + '\'' +
                '}';
    }
}
