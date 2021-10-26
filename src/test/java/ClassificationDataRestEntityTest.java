import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ClassificationDataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassificationDataRestEntityTest {
    @Test
    void defaultClientServerObject_didSucceed() throws JsonProcessingException {
        ClassificationDataRestEntity object = new ClassificationDataRestEntity("urn:apimap:1", "1");

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}}}");
    }

    @Test
    void defaultServerClientObject_didSucceed() throws URISyntaxException, JsonProcessingException {
        ClassificationDataRestEntity content = new ClassificationDataRestEntity("urn:apimap:1", "1");

        JsonApiRootObject object = new JsonApiRootObject<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}},\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }
}
