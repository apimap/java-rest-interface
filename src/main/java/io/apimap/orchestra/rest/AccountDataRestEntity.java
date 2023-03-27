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

package io.apimap.orchestra.rest;

import com.fasterxml.jackson.annotation.*;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Account",
        description = "Account"
)
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class AccountDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.ACCOUNT_ELEMENT;

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonIgnore
    protected String uri;

    public AccountDataRestEntity() {
    }

    public AccountDataRestEntity(final String id) {
        super(id);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    @Schema(
            name="Account Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {

    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public AccountDataRestEntity.Attributes getAttributes() {
        return new AccountDataRestEntity.Attributes(
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(JsonApiViews.Collection.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();

        if(uri != null){ links.put("self", uri); }

        if(links.size() < 1) return null;
        return links;
    }
}
