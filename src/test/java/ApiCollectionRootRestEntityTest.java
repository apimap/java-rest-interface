import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiCollectionDataRestEntity;
import io.apimap.api.rest.ApiCollectionRootRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRootObject;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiCollectionRootRestEntityTest {
    @Test
    void defaultClientServerObject_didSucceed() throws JsonProcessingException {
        ApiCollectionDataRestEntity element1 = new ApiCollectionDataRestEntity(
                "name",
                "codeRepository",
                "description",
                "status",
                "version",
                Arrays.asList(new String[]{"url1", "url2"}),
                null,
                null
        );

        ApiCollectionDataRestEntity element2 = new ApiCollectionDataRestEntity(
                "name2",
                "codeRepository2",
                "description2",
                "status2",
                "version2",
                Arrays.asList(new String[]{"url1", "url2"}),
                null,
                null
        );

        ApiCollectionRootRestEntity object = new ApiCollectionRootRestEntity();
        object.addEntity(element1);
        object.addEntity(element2);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":[{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]}},{\"id\":\"name2\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name2\",\"codeRepository\":\"codeRepository2\",\"description\":\"description2\",\"status\":\"status2\",\"version\":\"version2\",\"documentation\":[\"url1\",\"url2\"]}}]}");
    }

    @Test
    void defaultServerClientObject_didSucceed() throws JsonProcessingException, URISyntaxException {
        ApiCollectionDataRestEntity element1 = new ApiCollectionDataRestEntity(
                "name",
                "codeRepository",
                "description",
                "status",
                "version",
                Arrays.asList(new String[]{"url1", "url2"}),
                new java.net.URI("http://localhost:8080").toString(),
                null
        );

        ApiCollectionDataRestEntity element2 = new ApiCollectionDataRestEntity(
                "name2",
                "codeRepository2",
                "description2",
                "status2",
                "version2",
                Arrays.asList(new String[]{"url1", "url2"}),
                null,
                null
        );

        ApiCollectionRootRestEntity content = new ApiCollectionRootRestEntity();
        content.addEntity(element1);
        content.addEntity(element2);

        JsonApiRootObject object = new JsonApiRootObject<>(content);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.writeValueAsString(object), "{\"data\":[{\"id\":\"name\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name\",\"codeRepository\":\"codeRepository\",\"description\":\"description\",\"status\":\"status\",\"version\":\"version\",\"documentation\":[\"url1\",\"url2\"]},\"links\":{\"self\":\"http://localhost:8080\"}},{\"id\":\"name2\",\"type\":\"api:element\",\"attributes\":{\"name\":\"name2\",\"codeRepository\":\"codeRepository2\",\"description\":\"description2\",\"status\":\"status2\",\"version\":\"version2\",\"documentation\":[\"url1\",\"url2\"]}}],\"links\":{},\"meta\":{},\"jsonapi\":{\"version\":\"1.1\"}}");
    }
}
