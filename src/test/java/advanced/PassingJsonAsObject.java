package advanced;

import base.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class PassingJsonAsObject extends TestBase {
    @Test
    public void shouldGetPostUserWithQueryParam(){
        Post post =

               given()
                        .when()
                        .get(BASE_URL+"/posts/1")
                        .then()
                        .statusCode(200)
                        .extract().response()
                       .as(Post.class);

        Assert.assertEquals(post.getTitle(), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        Assert.assertEquals(post.getId(), 1);
    }
}
