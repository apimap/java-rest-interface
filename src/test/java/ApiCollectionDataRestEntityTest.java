import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiCollectionDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiCollectionDataRestEntityTest {
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
        assertEquals("{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]}}", objectMapper.writeValueAsString(object));
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

        JsonApiRestResponseWrapper<ApiCollectionDataRestEntity> object = new JsonApiRestResponseWrapper<ApiCollectionDataRestEntity>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals("{\"data\":{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]},\"links\":{\"self\":\"http://localhost:8080\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(object));
    }
}
