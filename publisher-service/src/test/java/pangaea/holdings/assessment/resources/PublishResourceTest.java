package pangaea.holdings.assessment.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PublishResourceTest {

    private final String TOPIC = "test1";

    @Test
    public void testPublishEndpoint() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Json.createObjectBuilder().add("name", "Akinmukomi Oluwaseun").build())
                .when().post("/publish/" + TOPIC);

        Assertions.assertEquals(response.getStatusCode(), 200);
    }
}