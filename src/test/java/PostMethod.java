import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostMethod {
    @Test
    public void createUser(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"first_name\":\"nitya\",\n" +
                "\t\"last_name\":\"priya\",\n" +
                "\t\"email\":\"nitya@gmail.com\"\n" +
                "}";
        Response postResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .post("/users/");
        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.getBody().asString());
        Assert.assertEquals(postResponse.getStatusCode(), 201, "Expected response code was 201 but found" + postResponse.getStatusCode());
        Assert.assertEquals(postResponse.jsonPath().get("first_name"),"nitya","Validated first name successfully");

    }
    @Test
    public void registerUser(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"email\":\"eve.holt@reqres.in\",\n" +
                "\t\"password\":\"nityaaa\"\n" +
                "}";
        Response postResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .post("/register/");
        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.getBody().asString());
        Assert.assertEquals(postResponse.getStatusCode(), 200, "Expected response code was 200 but found" + postResponse.getStatusCode());
        Assert.assertEquals(postResponse.getBody().asString().split(",")[1].split(":")[0],"\"token\"","A token is expected.");

    }
    @Test
    public void registerUnsuccessful(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"email\":\"eve.holt@reqres.in\"\n" +
                "}";
        Response postResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .post("/register/");
        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.getBody().asString());
        Assert.assertEquals(postResponse.getStatusCode(), 400, "Expected response code was 400 but found" + postResponse.getStatusCode());
        Assert.assertEquals(postResponse.jsonPath().get("error").toString(),"Missing password","Show an error that password is missing.");

    }
    @Test
    public void loginSuccessful(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"email\":\"eve.holt@reqres.in\",\n" +
                "\t\"password\":\"nityajnk\"\n" +
                "\n" +
                "}";
        Response postResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .post("/login/");
        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.getBody().asString());
        Assert.assertEquals(postResponse.getStatusCode(), 200, "Expected response code was 200 but found" + postResponse.getStatusCode());
        Assert.assertEquals(postResponse.getBody().asString().split(":")[0].split("\"")[1],"token","A token is expected.");

    }
    @Test
    public void loginUnSuccessful_1(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"email\":\"eve.holt@reres.in\",\n" +
                "\t\"password\":\"nitya\"\n" +
                "\n" +
                "}";
        Response postResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .post("/login/");
        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.getBody().asString());
        Assert.assertEquals(postResponse.getStatusCode(), 400, "Expected response code was 400 but found" + postResponse.getStatusCode());
        Assert.assertEquals(postResponse.jsonPath().get("error").toString(),"user not found","Show an error as user is not registered");

    }
    @Test
    public void loginUnSuccessful_2(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"email\":\"eve.holt@reres.in\"\n" +
                "}";
        Response postResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .post("/login/");
        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.getBody().asString());
        Assert.assertEquals(postResponse.getStatusCode(), 400, "Expected response code was 400 but found" + postResponse.getStatusCode());
        Assert.assertEquals(postResponse.jsonPath().get("error").toString(),"Missing password","Login error due to missing password.");

    }
}
