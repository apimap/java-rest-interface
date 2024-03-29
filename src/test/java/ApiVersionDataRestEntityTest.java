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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiVersionDataRestEntity;
import io.apimap.api.rest.ApiVersionRatingEntity;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiVersionDataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        ApiVersionDataRestEntity object = new ApiVersionDataRestEntity("1.0.0");
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":\"1.0.0\",\"type\":\"version:element\",\"attributes\":{\"version\":\"1.0.0\",\"created\":null}}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"type\":\"version:element\",\"attributes\":{\"version\":\"1.0.0\",\"created\":null}}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, ApiVersionDataRestEntity.class);
        JsonApiRestRequestWrapper<ApiVersionDataRestEntity> output = objectMapper.readValue(input, type);

        assertEquals("1.0.0", output.getData().getVersion());
    }

    @Test
    void generatedRestResponse_didSucceed() throws URISyntaxException, JsonProcessingException {
        ApiVersionDataRestEntity object = new ApiVersionDataRestEntity(
                "1.0.0",
                new Date(1),
                new java.net.URI("http://localhost:8080").toString()
        );

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":\"1.0.0\",\"type\":\"version:element\",\"attributes\":{\"version\":\"1.0.0\",\"created\":\"1970-01-01\"},\"links\":{\"self\":\"http://localhost:8080\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<DataRestEntity>(object)));
    }

    @Test
    void generatedRestResponseWithRating_didSucceed() throws URISyntaxException, JsonProcessingException {
        ApiVersionDataRestEntity object = new ApiVersionDataRestEntity(
                "1.0.0",
                new Date(1),
                new ApiVersionRatingEntity(2),
                new java.net.URI("http://localhost:8080").toString()
        );

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":\"1.0.0\",\"type\":\"version:element\",\"attributes\":{\"version\":\"1.0.0\",\"created\":\"1970-01-01\",\"rating\":{\"documentation\":2}},\"links\":{\"self\":\"http://localhost:8080\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestResponse_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"type\":\"version:element\",\"attributes\":{\"version\":\"1.0.0\",\"created\":null}}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, ApiVersionDataRestEntity.class);
        JsonApiRestResponseWrapper<ApiVersionDataRestEntity> output = objectMapper.readValue(input, type);

        assertEquals("1.0.0", output.getData().getVersion());
        assertEquals("version:element", output.getData().getType());
    }
}


