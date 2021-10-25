import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiDataApiMetadataEntity;
import io.apimap.api.rest.ApiDataRestEntity;
import io.apimap.api.rest.jsonapi.IgnoranceIntrospector;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiDataRestEntityTest {
    @Test
    void defaultClientServerObject_didSucceed() throws JsonProcessingException {
        ApiDataRestEntity object = new ApiDataRestEntity("name", "codeRepository");

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\"}}}", objectMapper.writeValueAsString(object));
    }

    @Test
    void defaultServerClientObject_didSucceed() throws URISyntaxException, JsonProcessingException {
        ApiDataApiMetadataEntity metadata = new ApiDataApiMetadataEntity("token");

        ApiDataRestEntity content = new ApiDataRestEntity(
                metadata,
                "name",
                "codeRepository",
                new java.net.URI("http://localhost:8080").toString(),
                null
        );

        JsonApiRootObject object = new JsonApiRootObject<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\"},\"links\":{\"self\":\"http://localhost:8080\"},\"meta\":{\"token\":\"token\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(object));
    }

    @Test
    void deserializeString_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"API Catalog Example API\",\"codeRepository\":null}}}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setAnnotationIntrospector(new IgnoranceIntrospector());

        ApiDataRestEntity output = objectMapper.readValue(input, ApiDataRestEntity.class);

        assertEquals("API Catalog Example API", output.getName());
        assertEquals("api:element", output.getType());
    }

    @Test
    void deserializeInsideRootContainerObject_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"API Catalog Example API\",\"type\":\"api:element\",\"relationships\":{\"version:collection\":{\"links\":{\"self\":\"http://localhost:8080/api/name/version\"},\"data\":[]}},\"attributes\":{\"name\":\"API Catalog Example API\",\"codeRepository\":null},\"meta\":{\"token\":\"8d97aa91-aee4-411b-b24b-7fd6569fcddf\"}},\"links\":{\"related\":[{\"rel\":\"api:collection\",\"href\":\"http://172.17.0.1:8080/api\"},{\"rel\":\"classification:collection\",\"href\":\"http://172.17.0.1:8080/classification\"},{\"rel\":\"taxonomy:collection\",\"href\":\"http://172.17.0.1:8080/taxonomy\"}],\"self\":\"http://172.17.0.1:8080/api/API+Catalog+Example+API\"},\"meta\":{\"Copyright\":\"Copyright (c) October 20, 2021 Telenor Norway\",\"Support\":\"Any questions? Please contact us @ https://prima.corp.telenor.no/confluence/display/APIEXP\",\"OpenAPI 3\":\"http://172.17.0.1:8080/documentation/openapi3\",\"Documentation\":\"API documentation available @ https://prima.corp.telenor.no/confluence/display/APIEXP\",\"millis\":\"79\",\"Host Identifier\":\"ace6709e-d154-4619-a07f-9b117947173a\"},\"jsonapi\":{\"version\":\"1.1\"}}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setAnnotationIntrospector(new IgnoranceIntrospector());

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRootObject.class, ApiDataRestEntity.class);
        JsonApiRootObject<ApiDataRestEntity> output = objectMapper.readValue(input, type);

        assertNotNull(output);
    }
}
