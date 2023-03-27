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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
        name="Management Access Token",
        description = "Access token"
)
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS")
public class AccessTokenDataRestEntity extends DataRestEntity {
    public static final String TYPE = JsonApiRestResponseWrapper.ACCESS_TOKEN_ELEMENT;

    public static final String CATEGORY_KEY = "category";
    public static final String TOKEN_KEY = "token";
    public static final String VALIDITY_KEY = "validity";


    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum CategoryType {
        @JsonProperty("CLI")
        CLI("CLI"),
        @JsonProperty("API")
        API("API"),
        @JsonProperty("unknown")
        UNKNOWN("unknown");

        public final String value;

        @JsonCreator
        CategoryType(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "ReferenceType{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

    @Schema(hidden = true)
    @JsonIgnore
    protected CategoryType category;

    @Schema(hidden = true)
    @JsonIgnore
    protected String token;

    @Schema(hidden = true)
    @JsonIgnore
    protected AccessTokenValidityDataRestEntity validity;

    @Schema(description = "Object type definition", defaultValue = TYPE, required = true)
    @JsonView(JsonApiViews.Default.class)
    protected String type = TYPE;

    @JsonIgnore
    protected String uri;

    public AccessTokenDataRestEntity() {
    }

    public AccessTokenDataRestEntity(final CategoryType category,
                                     final String token,
                                     final AccessTokenValidityDataRestEntity validity,
                                     final String identifier) {

        super(identifier);

        this.category = category;
        this.token = token;
        this.validity = validity != null ? (AccessTokenValidityDataRestEntity) validity.clone() : null;
    }

    public AccessTokenDataRestEntity(final String id,
                                     final CategoryType category,
                                     final String token,
                                     final AccessTokenValidityDataRestEntity validity,
                                     final String uri) {
        super(id);
        this.category = category;
        this.token = token;
        this.validity = validity != null ? (AccessTokenValidityDataRestEntity) validity.clone() : null;
        this.uri = uri;
    }

    public AccessTokenDataRestEntity(final CategoryType category,
                                     final String token,
                                     final Date validFrom,
                                     final Date validTo,
                                     final String identifier) {
        super(identifier);

        this.category = category;
        this.token = token;
        this.validity = new AccessTokenValidityDataRestEntity(validFrom, validTo);
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AccessTokenValidityDataRestEntity getValidity() {
        return validity != null ? (AccessTokenValidityDataRestEntity) validity.clone() : null;
    }

    public void setValidity(AccessTokenValidityDataRestEntity validity) {
        this.validity = validity != null ? (AccessTokenValidityDataRestEntity) validity.clone() : null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Schema(
            name="API Version Attributes",
            description = "Object attributes. This object must be used when doing a POST or PUT"
    )
    public static class Attributes implements Cloneable{
        @JsonProperty(CATEGORY_KEY)
        @Schema(description = "Access token type category",example = "API or CLI", required = true)
        @JsonView(JsonApiViews.Default.class)
        protected CategoryType category;

        @JsonView(JsonApiViews.Default.class)
        @Schema(description = "Access token",example = "Random value", required = false)
        protected String token;

        @JsonView(JsonApiViews.Default.class)
        @JsonProperty(VALIDITY_KEY)
        @Schema(description = "Validity", required = true)
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        protected AccessTokenValidityDataRestEntity validity;

        public Attributes(){

        }

        public Attributes(final CategoryType category,
                          final String token) {
            this.category = category;
            this.token = token;
        }

        public Attributes(final CategoryType category,
                          final String token,
                          final AccessTokenValidityDataRestEntity validity) {
            this.category = category;
            this.token = token;
            this.validity = validity != null ? (AccessTokenValidityDataRestEntity) validity.clone() : null;
        }

        public CategoryType getCategory() {
            return category;
        }

        public void setCategory(CategoryType category) {
            this.category = category;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public AccessTokenValidityDataRestEntity getValidity() {
            return validity != null ? (AccessTokenValidityDataRestEntity) validity.clone() : null;
        }

        public void setValidity(AccessTokenValidityDataRestEntity validity) {
            this.validity = validity != null ? (AccessTokenValidityDataRestEntity) validity.clone() : null;
        }

        @Override
        public Object clone(){
            Attributes returnValue = null;

            try {
                returnValue = (Attributes) super.clone();
            } catch (CloneNotSupportedException e) {
                returnValue = new Attributes();
            }

            returnValue.setCategory(this.category);
            returnValue.setToken(this.token);
            returnValue.setValidity( validity != null ? (AccessTokenValidityDataRestEntity) this.validity.clone() : null);

            return returnValue;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "category='" + category + '\'' +
                    ", token='" + token + '\'' +
                    ", validity=" + validity +
                    '}';
        }
    }

    @JsonProperty
    @JsonView(JsonApiViews.Default.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public AccessTokenDataRestEntity.Attributes getAttributes() {
        return new AccessTokenDataRestEntity.Attributes(
                category,
                token,
                validity
        );
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.category = CategoryType.valueOf((String) attributes.getOrDefault(CATEGORY_KEY, null));
        this.token = (String) attributes.getOrDefault(TOKEN_KEY, null);

        HashMap<String, Object> validity = (HashMap<String, Object>) attributes.getOrDefault(VALIDITY_KEY, null);
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.validity = new AccessTokenValidityDataRestEntity(
                dateFormat.parse(String.valueOf(validity.get("from"))),
                dateFormat.parse(String.valueOf(validity.get("to")))
            );
        } catch (ParseException ignore) {
            this.validity = new AccessTokenValidityDataRestEntity();
        }
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView({JsonApiViews.Default.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public HashMap<String, String> getLinks() {
        HashMap links = new HashMap();

        if(uri != null){ links.put("self", uri); }

        if(links.size() < 1) return null;
        return links;
    }

    @Override
    public String toString() {
        return "AccessTokenDataRestEntity{" +
                "category='" + category + '\'' +
                ", token='" + token + '\'' +
                ", validity=" + validity +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
