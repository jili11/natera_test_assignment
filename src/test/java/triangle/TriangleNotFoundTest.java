package triangle;

import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static triangle.utils.TriangleConstants.AREA_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.ONE_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.PATH_PARAM;
import static triangle.utils.TriangleConstants.PERIMETER_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.PERSONAL_TOKEN;
import static triangle.utils.TriangleConstants.X_USER_HEADER;

public class TriangleNotFoundTest {

    @Test
    public void getOneTriangleWithRandomToken() {
        given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .pathParam(PATH_PARAM, "1")
                .when()
                .request(Method.GET, ONE_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void deleteTriangleWithRandomToken() {
        given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .pathParam(PATH_PARAM, "1")
                .when()
                .request(Method.DELETE, ONE_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getPerimeterWithRandomToken() {
        given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .pathParam(PATH_PARAM, "1")
                .when()
                .request(Method.GET, PERIMETER_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getAreaWithRandomToken() {
        given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .pathParam(PATH_PARAM, "1")
                .when()
                .request(Method.GET, AREA_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
