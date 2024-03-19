package org.quarkusclub;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CadastroClienteTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/v1/cadastro")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}