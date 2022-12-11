package advanced;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PatchPost extends TestBase {

    @Test
    public void shouldUpdatePostWithLimitedBody(){
        Map<String, Object> post = new HashMap<>();

        post.put("title", "This is some new title");
        post.put("body", "text text");


        Response response =

                given()
                        .pathParam("postId","3")
                        .body(post)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(BASE_URL+"/posts"+"/{postId}")
                        .then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("id").toString(), "3");
        //Assert.assertNull(jsonPath.get("email"));
        //Assert.assertNull(jsonPath.get("company.name"));
        Assert.assertEquals(jsonPath.get("userId").toString(), "1");
        Assert.assertEquals(jsonPath.get("title"), "This is some new title");

        Assert.assertEquals(jsonPath.get("body"), "text text");
}
}

