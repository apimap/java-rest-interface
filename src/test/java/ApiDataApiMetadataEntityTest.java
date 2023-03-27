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
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiDataMetadataEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiDataApiMetadataEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        ApiDataMetadataEntity object = new ApiDataMetadataEntity("token");
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"token\":\"token\"}", objectMapper.writeValueAsString(object));
    }

    @Test
    void receivedRestRequest_didSucceed(){
        assertTrue(true); // TODO
    }

    @Test
    void generatedRestResponse_didSucceed(){
        assertTrue(true); // TODO
    }

    @Test
    void receivedRestResponse_didSucceed(){
        assertTrue(true); // TODO
    }
}
