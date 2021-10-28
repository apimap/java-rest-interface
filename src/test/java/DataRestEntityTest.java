import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.api.rest.RootRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        DataRestEntity object = new DataRestEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":null}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":null}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, DataRestEntity.class);
        JsonApiRestRequestWrapper output = objectMapper.readValue(input, type);

        assertNotNull(output.getData());
    }

    @Test
    void generatedRestResponse_didSucceed() throws JsonProcessingException {
        DataRestEntity object = new DataRestEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":null},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestResponse_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":null},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, DataRestEntity.class);
        JsonApiRestResponseWrapper output = objectMapper.readValue(input, type);

        assertNotNull(output.getData());
    }
}
