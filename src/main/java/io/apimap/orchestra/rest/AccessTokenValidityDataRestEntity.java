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
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;
import io.apimap.rest.jsonapi.JsonApiViews;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
public class AccessTokenValidityDataRestEntity implements Cloneable {
    public static final String TYPE = JsonApiRestResponseWrapper.ACCESS_TOKEN_VALIDITY_ELEMENT;

    public static final String VALID_FROM = "from";
    public static final String VALID_TO = "to";

    @JsonView(JsonApiViews.Default.class)
    @JsonProperty(VALID_FROM)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Date from", required = true)
    protected Date from;

    @JsonView(JsonApiViews.Default.class)
    @JsonProperty(VALID_TO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Date to", required = true)
    protected Date to;

    public AccessTokenValidityDataRestEntity() {
    }

    @JsonCreator
    public AccessTokenValidityDataRestEntity(final Date from,
                                             final Date to) {
        this.from = from != null ? (Date)from.clone() : null;
        this.to = to != null ? (Date)to.clone(): null;
    }

    public Date getFrom() {
        return from != null ? (Date)from.clone() : null;
    }

    public void setFrom(final Date from) {
        this.from = from != null ? (Date)from.clone() : null;
    }

    public Date getTo() {
        return to != null ? (Date)to.clone() : null;
    }

    public void setTo(final Date to) {
        this.to = to != null ? (Date)to.clone() : null;
    }

    @Override
    public Object clone(){
        AccessTokenValidityDataRestEntity returnValue = null;

        try {
            returnValue = (AccessTokenValidityDataRestEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            returnValue = new AccessTokenValidityDataRestEntity();
        }

        returnValue.setFrom(this.from != null ? (Date)this.from.clone() : null);
        returnValue.setTo(this.to != null ? (Date)this.to.clone() : null);

        return returnValue;
    }

    @Override
    public String toString() {
        return "AccessTokenValidityDataRestEntity{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
