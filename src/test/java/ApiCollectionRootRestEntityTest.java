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
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiCollectionDataRestEntity;
import io.apimap.api.rest.ApiCollectionRootRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiCollectionRootRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        ApiCollectionDataRestEntity element1 = new ApiCollectionDataRestEntity(
                "name",
                "codeRepository",
                "description",
                "status",
                "version",
                Arrays.asList(new String[]{"url1", "url2"}),
                null,
                null
        );

        ApiCollectionDataRestEntity element2 = new ApiCollectionDataRestEntity(
                "name2",
                "codeRepository2",
                "description2",
                "status2",
                "version2",
                Arrays.asList(new String[]{"url1", "url2"}),
                null,
                null
        );

        ApiCollectionRootRestEntity object = new ApiCollectionRootRestEntity();
        object.addEntity(element1);
        object.addEntity(element2);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":[{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]}},{\"id\":\"name2\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name2\",\"codeRepository\":\"codeRepository2\",\"description\":\"description2\",\"status\":\"status2\",\"version\":\"version2\",\"documentation\":[\"url1\",\"url2\"]}}]}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<ApiCollectionRootRestEntity>(object)));
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
