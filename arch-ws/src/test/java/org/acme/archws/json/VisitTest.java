package org.acme.archws.json;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(TestLifecyleManager.class)
public class VisitTest {

    private static Header acceptJson = new Header("Accept", "application/json");

    @Test
    public void getList() {
        given()
                .header(acceptJson)
            .when()
                .get("/visit")
            .then()
                .statusCode(200);
    }

    @Test
    void testAddAndGet() {
        Visit v = new Visit();
        v.name = "Heiko";

        Response r =
            given()
                    .contentType(ContentType.JSON)
                    .body(v)
                .when()
                    .post("/visit")
                .then()
                    .statusCode(200)
                .extract().response();
        Long id = r.body().as(Long.class);

        try {
            given()
                    .header(acceptJson)
                .when()
                    .get("/visit/" + id)
                .then()
                    .statusCode(200);
        }
        finally {
            given()
                    .header(acceptJson)
                .when()
                    .delete("/visit/" + id)
                .then()
                    .statusCode(200);
        }
    }
}
