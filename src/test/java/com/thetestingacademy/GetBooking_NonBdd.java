package com.thetestingacademy;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class GetBooking_NonBdd {
    //static RequestSpecification r = RestAssured.given();
    public static void main(String[] args) {
       // r.relaxedHTTPSValidation("TLS");
        RequestSpecification r = RestAssured.given();

        r.baseUri("https://restful-booker.herokuapp.com");

        r.basePath("/booking/2");
      //  r.given().spec(r);
      //  r.when().log().all();
      //  r.get();

       //Response resp =  r.request(Method.GET, "");
       Response resp = r.when().get();
       System.out.println("Response->"+resp.prettyPrint());
        System.out.println("The status received: " + resp.statusLine());

        ValidatableResponse vr = resp.then();
        vr.log().all().statusCode(200);
       // r.then().log().all().statusCode(201);




    }



}
