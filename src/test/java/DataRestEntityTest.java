import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataRestEntityTest {
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
        DataRestEntity object = new DataRestEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"id\":null}");
    }

    @Test
    void defaultServerClientObject_didSucceed() throws URISyntaxException, JsonProcessingException {
        DataRestEntity content = new DataRestEntity();

        JsonApiRestResponseWrapper object = new JsonApiRestResponseWrapper<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"id\":null},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }
}
