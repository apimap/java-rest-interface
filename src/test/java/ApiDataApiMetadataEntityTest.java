import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiDataApiMetadataEntity;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiDataApiMetadataEntityTest {
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
    void defaultServerClientObject_didSucceed() throws URISyntaxException, JsonProcessingException {
        ApiDataApiMetadataEntity object = new ApiDataApiMetadataEntity("token");

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"token\":\"token\"}");
    }
}
