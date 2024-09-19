package com.thetestingacademy.Testng;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class UpdateBooking {

    RequestSpecification r;
    Response resp;
    ValidatableResponse vr;


    @Test

    public void test_pull()
    {
        String payloadauth = "{\n" +
                "            \"username\":\"admin\",\n" +
                "                \"password\":\"password123\"\n" +
                "        }";
        String bookingid = "2";
        String token = "7a4cf3402eed16a";

        String payloadpatch = "{\n" +
                "    \"firstname\" : \"Nandhu\",\n" +
                "    \"lastname\" : \"Golla\",\n" +
                "    \"totalprice\" : 112,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2019-01-01\",\n" +
                "        \"checkout\" : \"2020-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Dinner\"\n" +
                "}";

        r = RestAssured.given();


        r.baseUri("https://restful-booker.herokuapp.com");
//        r.basePath("/auth");
        r.basePath("/booking/"+bookingid);
        r.contentType(ContentType.JSON);
        //r.accept();
        r.cookie("token",token);

       r.body(payloadpatch).log().all();
//
        resp = r.when().put();
//
        vr = resp.then().log().all();
        vr.log().all().statusCode(200);


    }
}
