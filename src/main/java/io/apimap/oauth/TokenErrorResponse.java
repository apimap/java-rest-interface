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
import sun.tools.jstat.Token;

@Schema(
        name="TokenErrorResponse",
        description = "TokenErrorResponse"
)
public class TokenErrorResponse extends TokenResponse {
    @Schema(description = "Error Message",  required = true)
    @JsonProperty("error")
    protected String error;

    public TokenErrorResponse() {
    }

    public TokenErrorResponse(final String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "TokenErrorResponse{" +
                "error='" + error + '\'' +
                '}';
    }
}
