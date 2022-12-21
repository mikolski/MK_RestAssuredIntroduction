package advanced;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static base.TestBase.BASE_URL;
import static io.restassured.RestAssured.given;

public class CreateNewPost extends TestBase {
    @Test
    public void ShouldCreateNewUser(){
        Post post = new Post(1,"some title", "some text");
        given()
                .body(post)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+"/posts")
                .then().statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/postSchema.json")));

    }
}
