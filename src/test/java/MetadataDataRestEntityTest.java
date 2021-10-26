import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.MetadataDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MetadataDataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed(){
        assertTrue(false);
    }

    @Test
    void receivedRestRequest_didSucceed(){

    }

    @Test
    void generatedRestResponse_didSucceed(){

    }

    @Test
    void receivedRestResponse_didSucceed(){

    }
    @Test
    void defaultClientServerObject_didSucceed() throws JsonProcessingException {
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
        assertEquals("{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}}}", objectMapper.writeValueAsString(object) );
    }

    @Test
    void defaultServerClientObject_didSucceed() throws URISyntaxException, JsonProcessingException {
        MetadataDataRestEntity content = new MetadataDataRestEntity(
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

        JsonApiRestResponseWrapper object = new JsonApiRestResponseWrapper<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }

    @Test
    void deserializeString_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}}}";

        ObjectMapper objectMapper = new ObjectMapper();

        MetadataDataRestEntity output = objectMapper.readValue(input, MetadataDataRestEntity.class);

        assertEquals("name", output.getName());
        assertEquals("visibility", output.getVisibility());
    }

    @Test
    void deserializeInsideRootContainerObject_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}},\"links\":{\"self\":\"http://localhost:8080/api/name/version/apiVersion/metadata\"},\"meta\":{\"Copyright\":\"Copyright (c) October 26, 2021 Telenor Norway\",\"Support\":\"Any questions? Please contact us @ https://prima.corp.telenor.no/confluence/display/APIEXP\",\"OpenAPI 3\":\"http://localhost:8080/documentation/openapi3\",\"Documentation\":\"API documentation available @ https://prima.corp.telenor.no/confluence/display/APIEXP\",\"millis\":\"24\",\"Host Identifier\":\"26f90e16-2f05-41e2-b40b-1272066c8860\"},\"jsonapi\":{\"version\":\"1.1\"}}";

        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, MetadataDataRestEntity.class);
        JsonApiRestResponseWrapper<MetadataDataRestEntity> output = objectMapper.readValue(input, type);

        assertNotNull(output);
    }
}
