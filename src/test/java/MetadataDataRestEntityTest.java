import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.api.rest.MetadataDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetadataDataRestEntityTest {
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
        assertEquals(objectMapper.writeValueAsString(object), "{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}}");
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

        JsonApiRootObject object = new JsonApiRootObject<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"id\":\"name#apiVersion\",\"type\":\"metadata:element\",\"attributes\":{\"name\":\"name\",\"visibility\":\"visibility\",\"description\":\"description\",\"api version\":\"apiVersion\",\"release status\":\"releaseStatus\",\"system identifier\":\"systemIdentifier\",\"documentation\":[\"url1\",\"url2\"],\"interface specification\":\"interfaceSpecification\",\"interface description language\":\"interfaceDescriptionLanguage\",\"architecture layer\":\"architectureLayer\",\"business unit\":\"businessUnit\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }
}
