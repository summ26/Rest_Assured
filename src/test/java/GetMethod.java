import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class GetMethod {

    @Test
    public void getUsers()
    {
        RestAssured.baseURI = "https://reqres.in/api/";
        Response response = given()
                .header("content-type","application/json")
                .get("/users");
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(),200,"Expected response code was 200 but found"+response.statusCode());
        Assert.assertEquals(response.jsonPath().get("total").toString(),"12","Number of total users validated successfully.");

    }
    @Test
    public void getParticularUser()
    {
        RestAssured.baseURI = "https://reqres.in/api/";
        Response response = given()
                .header("content-type","application/json")
                .get("/users/3");
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(),200,"Expected response code was 200 but found"+response.statusCode());
        Assert.assertEquals(response.jsonPath().get("data").toString().split(",")[2].trim().split("=")[1],"Emma","Validated the first name successfully");

    }
    @Test
    public void getUnknownUser()
    {
        RestAssured.baseURI = "https://reqres.in/api/";
        Response response = given()
                .header("content-type","application/json")
                .get("/users/30");
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(),404,"Expected response code was 404 but found"+response.statusCode());
        Assert.assertEquals(response.asString(),"{}","Expected Empty result.");

    }

}
