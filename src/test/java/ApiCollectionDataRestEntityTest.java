import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiCollectionDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiCollectionDataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
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
        assertEquals( "{\"data\":{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]}}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<ApiCollectionDataRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]}}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, ApiCollectionDataRestEntity.class);
        JsonApiRestRequestWrapper<ApiCollectionDataRestEntity> output = objectMapper.readValue(input, type);

        assertEquals("name", output.getData().getName());
        assertEquals("codeRepository", output.getData().getCodeRepository());
        assertEquals("description", output.getData().getDescription());
        assertEquals("status", output.getData().getStatus());
        assertEquals("version", output.getData().getVersion());
    }

    @Test
    void generatedRestResponse_didSucceed() throws JsonProcessingException, URISyntaxException {
        ApiCollectionDataRestEntity object = new ApiCollectionDataRestEntity(
                "name",
                "codeRepository",
                "description",
                "status",
                "version",
                Arrays.asList(new String[]{"url1", "url2"}),
                new java.net.URI("http://localhost:8080").toString(),
                null
        );

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]},\"links\":{\"self\":\"http://localhost:8080\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<ApiCollectionDataRestEntity>(object)));
    }

    @Test
    void receivedRestResponse_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]},\"links\":{\"self\":\"http://localhost:8080\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, ApiCollectionDataRestEntity.class);
        JsonApiRestResponseWrapper<ApiCollectionDataRestEntity> output = objectMapper.readValue(input, type);

        assertNotNull(output.getData());
    }
}
