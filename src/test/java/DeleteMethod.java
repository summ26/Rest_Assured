import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteMethod {
    @Test
    public void deleteUser(){
        RestAssured.baseURI = "https://reqres.in/api/";
        Response response = given()
                .header("content-type","application/json")
                .delete("/users/2");
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(),204,"Expected response code was 204 but found"+response.statusCode());

    }
    @Test
    public void deleteAllUsers(){
        RestAssured.baseURI = "https://reqres.in/api/";
        Response response = given()
                .header("content-type","application/json")
                .delete("/users");
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(),204,"Expected response code was 204 but found"+response.statusCode());

    }
}
