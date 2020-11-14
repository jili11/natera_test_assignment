package triangle;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import triangle.model.Triangle;

import static io.restassured.RestAssured.given;
import static triangle.utils.TriangleConstants.AREA_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.CREATE_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.GET_ALL_TRIANGLES_ENDPOINT;
import static triangle.utils.TriangleConstants.ONE_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.PATH_PARAM;
import static triangle.utils.TriangleConstants.PERIMETER_TRIANGLE_ENDPOINT;
import static triangle.utils.TriangleConstants.PERSONAL_TOKEN;
import static triangle.utils.TriangleConstants.X_USER_HEADER;

public class TriangeLogicTest {

    Triangle entity = new Triangle(";", "1;1;2");
    String triangleId;

    @BeforeClass
    public void clean() {
        Response response = given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .when().request(Method.GET, GET_ALL_TRIANGLES_ENDPOINT)
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Triangle[] triangles = response.as(Triangle[].class);
        for (Triangle triangle : triangles) {
            given().header(X_USER_HEADER, PERSONAL_TOKEN)
                    .pathParam(PATH_PARAM, triangle.getId())
                    .when().request(Method.DELETE, ONE_TRIANGLE_ENDPOINT)
                    .then().statusCode(HttpStatus.SC_OK);
        }
    }


    @Test
    public void create() {
        given().log().all().header(X_USER_HEADER, PERSONAL_TOKEN)
                .contentType(ContentType.JSON)
                .body(entity)
                .when().request(Method.POST, CREATE_TRIANGLE_ENDPOINT)
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "create")
    public void getAll() {
        Response response = given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .when().request(Method.GET, GET_ALL_TRIANGLES_ENDPOINT)
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Triangle[] triangles = response.as(Triangle[].class);
        Assert.assertEquals(triangles.length, 1);
        triangleId = triangles[0].getId();
    }

    @Test(dependsOnMethods = "getAll")
    public void getOne() {
        Response response = given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .pathParam(PATH_PARAM, triangleId)
                .when().request(Method.GET, ONE_TRIANGLE_ENDPOINT)
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Triangle triangle = response.as(Triangle.class);
        Assert.assertEquals(triangle, entity);
    }

    @Test(dependsOnMethods = "getAll")
    public void getPerimeter() {
        Float perimeter = given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .pathParam(PATH_PARAM, triangleId)
                .when().request(Method.GET, PERIMETER_TRIANGLE_ENDPOINT)
                .then().statusCode(HttpStatus.SC_OK)
                .extract().path("result");
        Assert.assertEquals(perimeter, entity.calculatePerimeter());
    }

    @Test(dependsOnMethods = "getAll")
    public void getArea() {
        Float area = given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .pathParam(PATH_PARAM, triangleId)
                .when().request(Method.GET, AREA_TRIANGLE_ENDPOINT)
                .then().statusCode(HttpStatus.SC_OK)
                .extract().path("result");
        Assert.assertEquals(area, entity.calculateArea());
    }

    @Test(dependsOnMethods = {"getArea", "getPerimeter", "getOne"}, alwaysRun = true)
    public void delete() {
        given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .pathParam(PATH_PARAM, triangleId)
                .when().request(Method.DELETE, ONE_TRIANGLE_ENDPOINT)
                .then().statusCode(HttpStatus.SC_OK);
        Response response = given().header(X_USER_HEADER, PERSONAL_TOKEN)
                .when().request(Method.GET, GET_ALL_TRIANGLES_ENDPOINT)
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Triangle[] triangles = response.as(Triangle[].class);
        Assert.assertEquals(triangles.length, 0);
    }


}
