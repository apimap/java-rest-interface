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
import io.apimap.api.rest.ClassificationDataRestEntity;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassificationDataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        ClassificationDataRestEntity object = new ClassificationDataRestEntity("urn:apimap:1", "1");
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, ClassificationDataRestEntity.class);
        JsonApiRestRequestWrapper<ClassificationDataRestEntity> output = objectMapper.readValue(input, type);

        assertEquals("urn:apimap:1", output.getData().getUrn());
        assertEquals("1", output.getData().getTaxonomyVersion());
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
