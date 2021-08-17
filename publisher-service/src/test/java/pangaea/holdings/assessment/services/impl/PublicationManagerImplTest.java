package pangaea.holdings.assessment.services.impl;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pangaea.holdings.assessment.services.PublicationManager;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;


@QuarkusTest
class PublicationManagerImplTest {

    @Inject
    PublicationManager publicationManager;

    private JsonObject sampleRequest;

    @BeforeEach
    void init(){
        JsonObject post = Json.createObjectBuilder().add("name","Akinmukomi Oluwaseun").build();

        sampleRequest = Json.createObjectBuilder().add("topic", "test1")
                .add("data", post)
                .build();
    }

    @Test
    void publishPost_ShouldSucceed_WhenTopicIsValid() {
        String response = publicationManager.publishPost("test1", sampleRequest);

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains("Successfully published to "));
    }

}