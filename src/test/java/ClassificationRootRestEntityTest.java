import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ClassificationDataRestEntity;
import io.apimap.api.rest.ClassificationRootRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassificationRootRestEntityTest {
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
        ClassificationDataRestEntity element1 = new ClassificationDataRestEntity("urn:apimap:1", "1");
        ClassificationDataRestEntity element2 = new ClassificationDataRestEntity("urn:apimap:2", "1");

        ClassificationRootRestEntity object = new ClassificationRootRestEntity();
        object.addEntity(element1);
        object.addEntity(element2);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":[{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}},{\"id\":\"urn:apimap:2#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:2\",\"taxonomyVersion\":\"1\"}}]}");
    }

    @Test
    void defaultServerClientObject_didSucceed() throws JsonProcessingException, URISyntaxException {
        ClassificationDataRestEntity element1 = new ClassificationDataRestEntity("urn:apimap:1", "1");
        ClassificationDataRestEntity element2 = new ClassificationDataRestEntity("urn:apimap:2", "1");

        ClassificationRootRestEntity content = new ClassificationRootRestEntity();
        content.addEntity(element1);
        content.addEntity(element2);

        JsonApiRestResponseWrapper object = new JsonApiRestResponseWrapper<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":[{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}},{\"id\":\"urn:apimap:2#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:2\",\"taxonomyVersion\":\"1\"}}],\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }
}
