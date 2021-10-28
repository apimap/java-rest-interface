import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiDataApiMetadataEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiDataApiMetadataEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        ApiDataApiMetadataEntity object = new ApiDataApiMetadataEntity("token");
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"token\":\"token\"}", objectMapper.writeValueAsString(object));
    }

    @Test
    void receivedRestRequest_didSucceed(){
        assertTrue(true); // TODO
    }

    @Test
    void generatedRestResponse_didSucceed(){
        assertTrue(true); // TODO
    }

    @Test
    void receivedRestResponse_didSucceed(){
        assertTrue(true); // TODO
    }
}
