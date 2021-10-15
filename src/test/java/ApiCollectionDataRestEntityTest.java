import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiCollectionDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiCollectionDataRestEntityTest {
    @Test
    void defaultClientServerObject_didSucceed() throws JsonProcessingException {
        ApiCollectionDataRestEntity object = new ApiCollectionDataRestEntity(
                "name",
                "codeRepository",
                "description",
                "status",
                "version",
                Arrays.asList(new String[]{"url1", "url2"}),
                null,
                null
        );

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]}}}");
    }

    @Test
    void defaultServerClientObject_didSucceed() throws JsonProcessingException, URISyntaxException {
        ApiCollectionDataRestEntity content = new ApiCollectionDataRestEntity(
                "name",
                "codeRepository",
                "description",
                "status",
                "version",
                Arrays.asList(new String[]{"url1", "url2"}),
                new java.net.URI("http://localhost:8080").toString(),
                null
        );

        JsonApiRootObject object = new JsonApiRootObject<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]},\"links\":{\"self\":\"http://localhost:8080\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }
}
