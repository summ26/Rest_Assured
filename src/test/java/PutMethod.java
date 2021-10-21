import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutMethod {
    @Test
    public void updateUser(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"first_name\":\"Nitya\",\n" +
                "\t\"last_name\":\"Priya\",\n" +
                "\t\"email\":\"nitya@gmail\"\n" +
                "}";
        Response putResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .put("/users/2");
        System.out.println(putResponse.statusCode());
        System.out.println(putResponse.getBody().asString());
        Assert.assertEquals(putResponse.getStatusCode(), 200, "Expected response code was 200 but found" + putResponse.getStatusCode());
        Assert.assertEquals(putResponse.getBody().asString().split(",")[3].split(":")[0],"\"updatedAt\"","Should have an updated timestamp.");

    }
    @Test
    public void updateUser_2(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"first_name\":\"Sunitha\",\n" +
                "\t\"last_name\":\"Gupta\",\n" +
                "\t\"email\":\"gupthasuny@gmail\"\n" +
                "}";
        Response putResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .put("/users/4");
        System.out.println(putResponse.statusCode());
        System.out.println(putResponse.getBody().asString());
        Assert.assertEquals(putResponse.getStatusCode(), 200, "Expected response code was 200 but found" + putResponse.getStatusCode());
        Assert.assertEquals(putResponse.getBody().asString().split(",")[3].split(":")[0],"\"updatedAt\"","Should have an updated timestamp.");

    }
}
