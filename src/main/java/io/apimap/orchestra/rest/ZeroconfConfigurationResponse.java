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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
    getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@Schema(
    name="Zeroconf Configuration",
    description = "Zeroconf Configuration"
)
public class ZeroconfConfigurationResponse {
    @Schema(description = "Endpoints to other services that clients use",  required = true)
    @JsonProperty("endpoint")
    protected Endpoint endpoint;

    public ZeroconfConfigurationResponse() {
    }

    public ZeroconfConfigurationResponse(Endpoint endpoint) {
        this.endpoint = endpoint != null ? (Endpoint) endpoint.clone() : null;
    }

    public Endpoint getEndpoint() {
        return endpoint != null ? (Endpoint) endpoint.clone() : null;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint != null ? (Endpoint) endpoint.clone() : null;
    }

    @Override
    public String toString() {
        return "ZeroconfConfigurationResponse{" +
            "endpoint=" + endpoint +
            '}';
    }

    public static class Endpoint implements Cloneable{
        @Schema(description = "orchestra",  required = true)
        @JsonProperty("orchestra")
        protected String orchestra;

        @Schema(description = "api",  required = true)
        @JsonProperty("api")
        protected String api;

        public Endpoint(String orchestra, String api) {
            this.orchestra = orchestra;
            this.api = api;
        }

        public Endpoint() {
        }

        public String getOrchestra() {
            return orchestra;
        }

        public void setOrchestra(String orchestra) {
            this.orchestra = orchestra;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        @Override
        public Object clone() {
            Endpoint returnValue = null;

            try {
                returnValue = (Endpoint) super.clone();
            } catch (CloneNotSupportedException e) {
                returnValue = new Endpoint();
            }

            returnValue.api = this.api;
            returnValue.orchestra = this.orchestra;

            return returnValue;
        }

        @Override
        public String toString() {
            return "Endpoint{" +
                "orchestra='" + orchestra + '\'' +
                ", api='" + api + '\'' +
                '}';
        }
    }
}
