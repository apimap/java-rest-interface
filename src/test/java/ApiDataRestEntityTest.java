import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiDataApiMetadataEntity;
import io.apimap.api.rest.ApiDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiDataRestEntityTest {
    @Test
    void defaultClientServerObject_didSucceed() throws JsonProcessingException {
        ApiDataRestEntity object = new ApiDataRestEntity("name", "codeRepository");

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\"}}}");
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
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\"},\"links\":{\"self\":\"http://localhost:8080\"},\"meta\":{\"token\":\"token\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }

    @Test
    void deserializeString_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"type\":\"api:element\",\"attributes\":{\"name\":\"API Catalog Example API\",\"codeRepository\":null}}}";

        ObjectMapper objectMapper = new ObjectMapper();
        ApiDataRestEntity output = objectMapper.readValue(input, ApiDataRestEntity.class);

        assertEquals("API Catalog Example API", output.getName());
        assertEquals("api:element", output.getType());
    }
}
