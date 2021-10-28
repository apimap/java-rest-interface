import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apimap.api.rest.ApiCollectionDataRestEntity;
import io.apimap.api.rest.ApiCollectionRootRestEntity;
import io.apimap.api.rest.ApiDataRestEntity;
import io.apimap.api.rest.DataRestEntity;
import io.apimap.api.rest.jsonapi.JsonApiRestRequestWrapper;
import io.apimap.api.rest.jsonapi.JsonApiRestResponseWrapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiCollectionRootRestEntityTest {
    @Test
    @Ignore
    void generateRestRequest_didSucceed() throws JsonProcessingException {
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
        assertEquals( "{\"data\":[{\\\"id\\\":\\\"name\\\",\\\"type\\\":\\\"api:element\\\",\\\"attributes\\\":{\\\"name\\\":\\\"name\\\",\\\"codeRepository\\\":\\\"codeRepository\\\",\\\"description\\\":\\\"description\\\",\\\"status\\\":\\\"status\\\",\\\"version\\\":\\\"version\\\",\\\"documentation\\\":[\\\"url1\\\",\\\"url2\\\"]}},{\\\"id\\\":\\\"name2\\\",\\\"type\\\":\\\"api:element\\\",\\\"attributes\\\":{\\\"name\\\":\\\"name2\\\",\\\"codeRepository\\\":\\\"codeRepository2\\\",\\\"description\\\":\\\"description2\\\",\\\"status\\\":\\\"status2\\\",\\\"version\\\":\\\"version2\\\",\\\"documentation\\\":[\\\"url1\\\",\\\"url2\\\"]}}]}", objectMapper.writeValueAsString(new JsonApiRestRequestWrapper<ApiCollectionRootRestEntity>(object)));
    }

    @Test
    @Ignore
    void receivedRestRequest_didSucceed(){

    }

    @Test
    @Ignore
    void generatedRestResponse_didSucceed(){

    }

    @Test
    @Ignore
    void receivedRestResponse_didSucceed(){

    }
}
