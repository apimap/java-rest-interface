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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiDataApiMetadataEntity;
import io.apimap.api.rest.ApiDataRestEntity;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiDataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        ApiDataRestEntity object = new ApiDataRestEntity("name", "codeRepository");
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\"}}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\"}}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, ApiDataRestEntity.class);
        JsonApiRestRequestWrapper<ApiDataRestEntity> output = objectMapper.readValue(input, type);

        assertEquals("name", output.getData().getName());
        assertEquals("codeRepository", output.getData().getCodeRepository());
    }

    @Test
    void generatedRestResponse_didSucceed() throws JsonProcessingException, URISyntaxException {
        ApiDataRestEntity object = new ApiDataRestEntity(
                new ApiDataApiMetadataEntity("token"),
                "name",
                "codeRepository",
                new java.net.URI("http://localhost:8080").toString(),
                null
        );

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\"},\"links\":{\"self\":\"http://localhost:8080\"},\"meta\":{\"token\":\"token\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestResponse_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\"},\"links\":{\"self\":\"http://localhost:8080\"},\"meta\":{\"token\":\"token\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, ApiDataRestEntity.class);
        JsonApiRestResponseWrapper<ApiDataRestEntity> output = objectMapper.readValue(input, type);

        assertNotNull(output.getData());
    }
}

