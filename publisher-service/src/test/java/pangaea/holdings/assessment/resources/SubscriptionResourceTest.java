package pangaea.holdings.assessment.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.json.Json;

import static io.restassured.RestAssured.given;

@QuarkusTest
class SubscriptionResourceTest {

    private final String TOPIC = "test1";

    private final String URL = "http://localhost:9000/test1";

    @Test
    void subscribe() {

        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Json.createObjectBuilder().add("url", URL).build())
                .when().post("/subscribe/" + TOPIC);

        Assertions.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    void fetchSubscriptions() {
        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/subscribe/" + TOPIC);

        Assertions.assertEquals(response.getStatusCode(), 200);

    }


}