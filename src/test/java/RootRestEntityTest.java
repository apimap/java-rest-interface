import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiDataRestEntity;
import io.apimap.api.rest.RootRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RootRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        RootRestEntity object = new RootRestEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<RootRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, RootRestEntity.class);
        JsonApiRestRequestWrapper output = objectMapper.readValue(input, type);

        assertNotNull(output.getData());
    }

    @Test
    void generatedRestResponse_didSucceed() throws JsonProcessingException {
        RootRestEntity object = new RootRestEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<RootRestEntity>(object)));
    }

    @Test
    void receivedRestResponse_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, RootRestEntity.class);
        JsonApiRestResponseWrapper output = objectMapper.readValue(input, type);

        assertNotNull(output.getData());
    }
}
