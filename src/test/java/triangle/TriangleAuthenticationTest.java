package triangle;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import triangle.model.Triangle;

import static io.restassured.RestAssured.given;
import static triangle.utils.TriangleConstants.AREA_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.CREATE_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.GET_ALL_TRIANGLES_ENDPOINT;
import static triangle.utils.TriangleConstants.ONE_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.PATH_PARAM;
import static triangle.utils.TriangleConstants.PERIMETER_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.X_USER_HEADER;

public class TriangleAuthenticationTest {

    String token = RandomStringUtils.randomAlphabetic(8);

    @Test
    public void getAllThrianglesWithRandomToken() {
        given().header(X_USER_HEADER, token)
                .when()
                .request(Method.GET, GET_ALL_TRIANGLES_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getOneTriangleWithRandomToken() {
        given().header(X_USER_HEADER, token)
                .pathParam(PATH_PARAM, "1")
                .when()
                .request(Method.GET, ONE_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void deleteTriangleWithRandomToken() {
        given().header(X_USER_HEADER, token)
                .pathParam(PATH_PARAM, "1")
                .when()
                .request(Method.DELETE, ONE_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getPerimeterWithRandomToken() {
        given().header(X_USER_HEADER, token)
                .pathParam(PATH_PARAM, "1")
                .when()
                .request(Method.GET, PERIMETER_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getAreaWithRandomToken() {
        given().header(X_USER_HEADER, token)
                .pathParam(PATH_PARAM, "1")
                .when()
                .request(Method.GET, AREA_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void createTriangleRandomToken() {
        given().header(X_USER_HEADER, token)
                .contentType(ContentType.JSON)
                .body(new Triangle(",", "5,5,8"))
                .when()
                .request(Method.POST, CREATE_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

}
