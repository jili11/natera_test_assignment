package triangle;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import triangle.model.Triangle;

import static io.restassured.RestAssured.given;
import static triangle.utils.TriangleConstants.CREATE_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.PERSONAL_TOKEN;
import static triangle.utils.TriangleConstants.UNPROCESSABLE_STATUS_CODE;
import static triangle.utils.TriangleConstants.X_USER_HEADER;

public class TriangleNegativeTest {

    @DataProvider(name = "wrongTriangles")
    public static Object[][] endpoints() {
        return new Object[][]{
                {new Triangle(null, "123")},
                {new Triangle(null, "1,2,3")},
                {new Triangle("", "1;2;3")},
                {new Triangle(",", "1;2;3")},
                {new Triangle(";", "2;3")},
                {new Triangle(";", ";;")},
                {new Triangle(";", null)},
                {new Triangle("tyt")}
        };
    }

    @Test(dataProvider = "wrongTriangles")
    public void createTriangleUnprocessible(Triangle triangle) {

        given().log().all()
                .header(X_USER_HEADER, PERSONAL_TOKEN)
                .contentType(ContentType.JSON)
                .body(triangle)
                .when()
                .request(Method.POST, CREATE_TRIANGLE_ENDPOINT)
                .then()
                .statusCode(UNPROCESSABLE_STATUS_CODE);

    }
}
