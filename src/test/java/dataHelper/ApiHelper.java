package dataHelper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public ApiHelper() {
    }

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(System.getProperty("sut.url"))
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String debitCard(CardInfo card) {
        return given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/api/v1/pay")
                .then()
                .log().body()
                .statusCode(200)
                .extract().jsonPath().getString("status");
    }

    public static String creditCard(CardInfo card) {
        return given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("status");
    }
}