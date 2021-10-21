import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchMethod {
    @Test
    public void updateUserDetails(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"first_name\":\"nitya\"\n" +
                "}";
        Response patchResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .patch("/users/2");
        System.out.println(patchResponse.statusCode());
        System.out.println(patchResponse.getBody().asString());
        Assert.assertEquals(patchResponse.getStatusCode(), 200, "Expected response code was 200 but found" + patchResponse.getStatusCode());
        Assert.assertEquals(patchResponse.getBody().asString().split(",")[1].split(":")[0],"\"updatedAt\"","Should have an updated timestamp.");

    }
    @Test
    public void updateUserDetails_2(){
        RestAssured.baseURI = "https://reqres.in/api/";
        String reqbody = "{\n" +
                "\t\"email\":\"abc@ok.com\"\n" +
                "}";
        Response patchResponse = given()
                .header("content-type", "application/json")
                .body(reqbody)
                .patch("/users/2");
        System.out.println(patchResponse.statusCode());
        System.out.println(patchResponse.getBody().asString());
        Assert.assertEquals(patchResponse.getStatusCode(), 200, "Expected response code was 200 but found" + patchResponse.getStatusCode());
        Assert.assertEquals(patchResponse.getBody().asString().split(",")[1].split(":")[0],"\"updatedAt\"","Should have an updated timestamp.");

    }
}
