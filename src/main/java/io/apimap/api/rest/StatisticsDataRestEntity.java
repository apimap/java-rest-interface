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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import io.apimap.api.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Statistics Item"
)
public class StatisticsDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRootObject.STATISTICS_ENTRY;
    public static final String KEY_KEY = "key";
    public static final String VALUE_KEY = "value";

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonProperty(KEY_KEY)
    @Schema(hidden = true)
    protected String key;

    @JsonProperty(VALUE_KEY)
    @Schema(hidden = true)
    protected String value;

    public StatisticsDataRestEntity() {
    }

    public StatisticsDataRestEntity(String id, String key, String value) {
        this.id = id;
        this.value = value;
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    @Schema(
            name="Statistics Item Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes {
        @JsonProperty(KEY_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String key;

        @JsonProperty(VALUE_KEY)
        @JsonView(JsonApiViews.Default.class)
        protected String value;

        public Attributes(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Attributes getAttributes() {
        return new Attributes(
                key,
                value
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.value = (String) attributes.getOrDefault("value", null);
        this.key = (String) attributes.getOrDefault("key", null);
    }

    @Override
    public String toString() {
        return "StatisticsDataRestEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
