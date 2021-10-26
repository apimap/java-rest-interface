import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiVersionDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiVersionDataRestEntityTest {
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
        ApiVersionDataRestEntity object = new ApiVersionDataRestEntity("1.0.0");

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"type\":\"version:element\",\"attributes\":{\"version\":\"1.0.0\",\"created\":null}}}");
    }

    @Test
    void defaultServerClientObject_didSucceed() throws URISyntaxException, JsonProcessingException {
        ApiVersionDataRestEntity content = new ApiVersionDataRestEntity(
                "1.0.0",
                new Date(1),
                new java.net.URI("http://localhost:8080").toString()
        );

        JsonApiRestResponseWrapper object = new JsonApiRestResponseWrapper<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"id\":\"1.0.0\",\"type\":\"version:element\",\"attributes\":{\"version\":\"1.0.0\",\"created\":\"1970-01-01\"},\"links\":{\"self\":\"http://localhost:8080\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }

    @Test
    void deserializeString_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"type\":\"version:element\",\"attributes\":{\"version\":\"1.0.0\",\"created\":null}}}";

        ObjectMapper objectMapper = new ObjectMapper();
        ApiVersionDataRestEntity output = objectMapper.readValue(input, ApiVersionDataRestEntity.class);

        assertEquals("1.0.0", output.getVersion());
        assertEquals("version:element", output.getType());
    }
}


