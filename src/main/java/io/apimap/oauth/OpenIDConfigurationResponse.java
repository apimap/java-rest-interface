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

package io.apimap.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name=".well-known/openid-configuration",
        description = ".well-known/openid-configuration"
)
public class OpenIDConfigurationResponse {
    @Schema(description = "issuer",  required = true)
    @JsonProperty("issuer")
    protected String issuer;

    @Schema(description = "jwks_uri",  required = true)
    @JsonProperty("jwks_uri")
    protected String jwksUri;

    public OpenIDConfigurationResponse(final String issuer,
                                       final String jwksUri) {
        this.issuer = issuer;
        this.jwksUri = jwksUri;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(final String issuer) {
        this.issuer = issuer;
    }

    public String getJwksUri() {
        return jwksUri;
    }

    public void setJwksUri(final String jwksUri) {
        this.jwksUri = jwksUri;
    }

    @Override
    public String toString() {
        return "OpenIDConfigurationResponse{" +
                "issuer='" + issuer + '\'' +
                ", jwksUri='" + jwksUri + '\'' +
                '}';
    }
}
