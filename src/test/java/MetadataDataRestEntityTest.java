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
import io.apimap.api.rest.DataRestEntity;
import io.apimap.api.rest.MetadataDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetadataDataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        MetadataDataRestEntity object = new MetadataDataRestEntity(
                "name",
                "description",
                "visibility",
                "apiVersion",
                "releaseStatus",
                "interfaceSpecification",
                "interfaceDescriptionLanguage",
                "architectureLayer",
                "businessUnit",
                "systemIdentifier",
                Arrays.asList(new String[]{"url1", "url2"})
        );

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, MetadataDataRestEntity.class);
        JsonApiRestRequestWrapper<MetadataDataRestEntity> output = objectMapper.readValue(input, type);

        assertEquals("name", output.getData().getName());
        assertEquals("description", output.getData().getDescription());
        assertEquals("visibility", output.getData().getVisibility());
        assertEquals("apiVersion", output.getData().getApiVersion());
        assertEquals("releaseStatus", output.getData().getReleaseStatus());
        assertEquals("interfaceSpecification", output.getData().getInterfaceSpecification());
        assertEquals("interfaceDescriptionLanguage", output.getData().getInterfaceDescriptionLanguage());
        assertEquals("architectureLayer", output.getData().getArchitectureLayer());
        assertEquals("businessUnit", output.getData().getBusinessUnit());
        assertEquals("systemIdentifier", output.getData().getSystemIdentifier());
    }

    @Test
    void generatedRestResponse_didSucceed() throws JsonProcessingException {
        MetadataDataRestEntity object = new MetadataDataRestEntity(
                "name",
                "description",
                "visibility",
                "apiVersion",
                "releaseStatus",
                "interfaceSpecification",
                "interfaceDescriptionLanguage",
                "architectureLayer",
                "businessUnit",
                "systemIdentifier",
                Arrays.asList(new String[]{"url1", "url2"})
        );

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestResponse_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, MetadataDataRestEntity.class);
        JsonApiRestResponseWrapper<MetadataDataRestEntity> output = objectMapper.readValue(input, type);

        assertEquals("name", output.getData().getName());
        assertEquals("description", output.getData().getDescription());
        assertEquals("visibility", output.getData().getVisibility());
        assertEquals("apiVersion", output.getData().getApiVersion());
        assertEquals("releaseStatus", output.getData().getReleaseStatus());
        assertEquals("interfaceSpecification", output.getData().getInterfaceSpecification());
        assertEquals("interfaceDescriptionLanguage", output.getData().getInterfaceDescriptionLanguage());
        assertEquals("architectureLayer", output.getData().getArchitectureLayer());
        assertEquals("businessUnit", output.getData().getBusinessUnit());
        assertEquals("systemIdentifier", output.getData().getSystemIdentifier());
    }
}
