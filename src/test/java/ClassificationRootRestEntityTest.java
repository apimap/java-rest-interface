import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ClassificationDataRestEntity;
import io.apimap.api.rest.ClassificationRootRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassificationRootRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        ClassificationDataRestEntity element1 = new ClassificationDataRestEntity("urn:apimap:1", "1");
        ClassificationDataRestEntity element2 = new ClassificationDataRestEntity("urn:apimap:2", "1");

        ClassificationRootRestEntity object = new ClassificationRootRestEntity();
        object.addEntity(element1);
        object.addEntity(element2);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":[{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}},{\"id\":\"urn:apimap:2#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:2\",\"taxonomyVersion\":\"1\"}}]}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<ClassificationRootRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":[{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}},{\"id\":\"urn:apimap:2#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:2\",\"taxonomyVersion\":\"1\"}}]}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, ClassificationRootRestEntity.class);
        JsonApiRestRequestWrapper<ClassificationRootRestEntity> output = objectMapper.readValue(input, type);

        assertEquals(2, output.getData().getData().size());
    }

    @Test
    void generatedRestResponse_didSucceed() throws JsonProcessingException {
        ClassificationDataRestEntity element1 = new ClassificationDataRestEntity("urn:apimap:1", "1");
        ClassificationDataRestEntity element2 = new ClassificationDataRestEntity("urn:apimap:2", "1");

        ClassificationRootRestEntity object = new ClassificationRootRestEntity();
        object.addEntity(element1);
        object.addEntity(element2);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":[{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}},{\"id\":\"urn:apimap:2#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:2\",\"taxonomyVersion\":\"1\"}}],\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}", objectMapper.writeValueAsString(new JsonApiRestResponseWrapper<ClassificationRootRestEntity>(object)));
    }

    @Test
    void receivedRestResponse_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":[{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}},{\"id\":\"urn:apimap:2#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:2\",\"taxonomyVersion\":\"1\"}}],\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestResponseWrapper.class, ClassificationRootRestEntity.class);
        JsonApiRestResponseWrapper<ClassificationRootRestEntity> output = objectMapper.readValue(input, type);

        assertEquals(2, output.getData().getData().size());
    }
}
