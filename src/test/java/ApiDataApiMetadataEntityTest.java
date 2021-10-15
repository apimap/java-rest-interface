import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiDataApiMetadataEntity;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiDataApiMetadataEntityTest {
    @Test
    void defaultServerClientObject_didSucceed() throws URISyntaxException, JsonProcessingException {
        ApiDataApiMetadataEntity object = new ApiDataApiMetadataEntity("token");

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"token\":\"token\"}");
    }
}
