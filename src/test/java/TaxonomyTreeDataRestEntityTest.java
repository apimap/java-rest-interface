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
import io.apimap.api.rest.TaxonomyDataRestEntity;
import io.apimap.api.rest.TaxonomyTreeDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaxonomyTreeDataRestEntityTest {

    @Test
    void stringToEnum_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"type\":\"urn:element\",\"attributes\":{\"type\":\"reference\"}}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, TaxonomyDataRestEntity.class);
        JsonApiRestRequestWrapper<TaxonomyDataRestEntity> output = objectMapper.readValue(input, type);

        assertSame(output.getData().getReferenceType(), TaxonomyTreeDataRestEntity.ReferenceType.REFERENCE);
    }

    @Test
    void enumToString_didSucceed() throws JsonProcessingException {
        TaxonomyTreeDataRestEntity object = new TaxonomyTreeDataRestEntity(
                null,
                null,
                null,
                null,
                null,
                null,
                TaxonomyDataRestEntity.ReferenceType.REFERENCE,
                null
        );

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"type\":\"urn:element\",\"attributes\":{\"urn\":null,\"title\":null,\"url\":null,\"description\":null,\"type\":\"reference\",\"entities\":null},\"links\":{\"self\":null}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<TaxonomyDataRestEntity>(object)));
    }

    @Test
    void generateRestRequest_didSucceed(){
        assertTrue(true); // TODO
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
