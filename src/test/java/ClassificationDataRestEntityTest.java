import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ClassificationDataRestEntity;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassificationDataRestEntityTest {
    @Test
    void generateRestRequest_didSucceed() throws JsonProcessingException {
        ClassificationDataRestEntity object = new ClassificationDataRestEntity("urn:apimap:1", "1");
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals( "{\"data\":{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}}}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<DataRestEntity>(object)));
    }

    @Test
    void receivedRestRequest_didSucceed() throws JsonProcessingException {
        String input = "{\"data\":{\"id\":\"urn:apimap:1#1\",\"type\":\"classification:element\",\"attributes\":{\"urn\":\"urn:apimap:1\",\"taxonomyVersion\":\"1\"}}}";
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(JsonApiRestRequestWrapper.class, ClassificationDataRestEntity.class);
        JsonApiRestRequestWrapper<ClassificationDataRestEntity> output = objectMapper.readValue(input, type);

        assertEquals("urn:apimap:1", output.getData().getUrn());
        assertEquals("1", output.getData().getTaxonomyVersion());
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
