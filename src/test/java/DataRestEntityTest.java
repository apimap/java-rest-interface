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
import io.apimap.api.rest.DataRestEntity;
import io.apimap.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        DataRestEntity object = new DataRestEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":null}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":null}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, DataRestEntity.class);
        JsonApiRestRequestWrapper output = objectMapper.readValue(input, type);

        assertNotNull(output.getData());
    }

    @Test
    void generatedRestResponse_didSucceed() throws JsonProcessingException {
        DataRestEntity object = new DataRestEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":null},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestResponse_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":null},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, DataRestEntity.class);
        JsonApiRestResponseWrapper output = objectMapper.readValue(input, type);

        assertNotNull(output.getData());
    }
}
