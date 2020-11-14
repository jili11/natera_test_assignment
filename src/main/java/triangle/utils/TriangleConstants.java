package triangle.utils;

public class TriangleConstants {

    public static final String X_USER_HEADER = "X-User";

    //masked on purpose
    public static final String PERSONAL_TOKEN = "xxxxxx-xxxx-xxxx-xxxx-xxxxxxxx";

    public static final int UNPROCESSABLE_STATUS_CODE = 422;

    public static final String DEFAULT_SEPARATOR = ";";

    public static final String GET_ALL_TRIANGLES_ENDPOINT = "https://qa-quiz.natera.com/triangle/all";

    public static final String CREATE_TRIANGLE_ENDPOINT = "https://qa-quiz.natera.com/triangle";

    public static final String ONE_TRIANGLE_ENDPOINT = "https://qa-quiz.natera.com/triangle/{triangleId}";

    public static final String PERIMETER_TRIANGLE_ENDPOINT = "https://qa-quiz.natera.com/triangle/{triangleId}/perimeter";

    public static final String AREA_TRIANGLE_ENDPOINT = "https://qa-quiz.natera.com/triangle/{triangleId}/area";

    public static final String PATH_PARAM = "triangleId";


}
