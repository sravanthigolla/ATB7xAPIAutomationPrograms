package com.thetestingacademy;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class CreateToken_Post {

    public static void main(String[] args) {
        // r.relaxedHTTPSValidation("TLS");

        String payload = "{\n" +
                "            \"username\":\"admin\",\n" +
                "                \"password\":\"password123\"\n" +
                "        }";
//Given
                RequestSpecification r = RestAssured.given();
                r.baseUri("https://restful-booker.herokuapp.com");
                r.basePath("/auth");
                r.contentType(ContentType.JSON).log().all();
                r.body(payload);
//when
                Response resp = r.when().post();

  //then
        ValidatableResponse vr = resp.then();
        vr.log().all().statusCode(200);






    }



}
