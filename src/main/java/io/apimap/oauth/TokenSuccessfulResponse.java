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
        name="TokenSuccessfulResponse",
        description = "TokenSuccessfulResponse"
)
public class TokenSuccessfulResponse extends TokenResponse {
    @Schema(description = "Access token",  required = true)
    @JsonProperty("access_token")
    protected String accessToken;

    @Schema(description = "Token type",  required = true)
    @JsonProperty("token_type")
    protected String tokenType = "Bearer";

    @Schema(description = "Refresh Token",  required = true)
    @JsonProperty("refresh_token")
    protected String refreshToken;

    @Schema(description = "expires in",  required = true)
    @JsonProperty("expires_in")
    protected Integer expiresIn;

    @Schema(description = "id token",  required = true)
    @JsonProperty("id_token")
    protected String idToken;

    public TokenSuccessfulResponse() {
    }

    public TokenSuccessfulResponse(final String accessToken,
                                   final Integer expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public TokenSuccessfulResponse(final String accessToken,
                                   final String tokenType,
                                   final String refreshToken,
                                   final Integer expiresIn,
                                   final String idToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.idToken = idToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public String toString() {
        return "TokenSuccessfulResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", idToken='" + idToken + '\'' +
                '}';
    }
}
