import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetTodos extends TestBase {
    private String todos = "/todos";
    private String todoId4 = "/4";
    private String userId1 = "?userId=1";

    private String completed1 = "&completed=1";

    @Test
    public void shouldGetAllTodos(){
        when()
                .get(BASE_URL+todos)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetTodoById(){
        Response response =

            when()
                    .get(BASE_URL+todos+todoId4)
                    .then()
                    .statusCode(200)
                    .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("id").toString(),"4");
    }

    @Test
    public void shouldGetTodosByUserId(){
        when()
                .get(BASE_URL+todos+userId1)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetTodosByUserIdAndCompletedState(){
        when()
                .get(BASE_URL+todos+userId1+completed1)
                .then()
                .statusCode(200);
    }

}
