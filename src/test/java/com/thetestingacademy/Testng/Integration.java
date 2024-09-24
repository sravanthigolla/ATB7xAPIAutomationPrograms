package com.thetestingacademy.Testng;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class Integration {
     RequestSpecification r;
     ValidatableResponse vr;
     Response resp;
String token;
Integer bookingId;


    @BeforeTest
    public String getToken(){

        String payload = "{\n" +
                "            \"username\":\"admin\",\n" +
                "                \"password\":\"password123\"\n" +
                "        }";


        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON).log().all();
        r.body(payload);
        Response resp = r.when().post();
        ValidatableResponse vr = resp.then();
        vr.log().all().statusCode(200);
        token = resp.jsonPath().getString("token");
        assertThat(token).isNotNull();
        System.out.println(token);
        return token;
    }


    public Integer getBookingID(){



     String payload_create = "{\n" +
             "    \"firstname\" : \"Jam\",\n" +
             "    \"lastname\" : \"Brown\",\n" +
             "    \"totalprice\" : 111,\n" +
             "    \"depositpaid\" : true,\n" +
             "    \"bookingdates\" : {\n" +
             "        \"checkin\" : \"2019-01-01\",\n" +
             "        \"checkout\" : \"2019-01-01\"\n" +
             "    },\n" +
             "    \"additionalneeds\" : \"Breakfast\"\n" +
             "}";

        r = RestAssured.given();


        r.baseUri("https://restful-booker.herokuapp.com");
//        r.basePath("/auth");
        r.basePath("/booking/");
        r.contentType(ContentType.JSON);
        //r.accept();


        r.body(payload_create).log().all();
//
        resp = r.when().post();
//
        vr = resp.then().log().all();
        vr.log().all().statusCode(200);
        bookingId = resp.jsonPath().getInt("bookingid");
        assertThat(bookingId).isNotNull().isNotZero().isPositive();



        System.out.println(bookingId);
        return bookingId;

    }

    @Test
    public void updateBookingID(){

        bookingId = getBookingID();
        token = getToken();

        String payloadpatch = "{\n" +
                "    \"firstname\" : \"Nanda\",\n" +
                "    \"lastname\" : \"Golla\",\n" +
                "    \"totalprice\" : 112,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2020-01-01\",\n" +
                "        \"checkout\" : \"2020-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Dinner\"\n" +
                "}";

        r = RestAssured.given();


        r.baseUri("https://restful-booker.herokuapp.com");

        r.basePath("/booking/"+bookingId);
        r.contentType(ContentType.JSON);
        //r.accept();
        r.cookie("token",token);

        r.body(payloadpatch).log().all();
//
        resp = r.when().put();
      String firstname = resp.then().extract().path("firstname");
        vr = resp.then().log().all();
        vr.log().all().statusCode(200);

        assertThat(firstname).isEqualTo("Nanda").isNotEmpty().isNotBlank();


    }

    @Test
    public void getUpdatedBookingID(){

        System.out.println(bookingId);

        r = RestAssured.given();


        r.baseUri("https://restful-booker.herokuapp.com");

        r.basePath("/booking/"+bookingId);
        r.contentType(ContentType.JSON);





        resp = r.when().get();
        String firstname = resp.then().extract().path("firstname");
        vr = resp.then().log().all();
        vr.log().all().statusCode(200);
        assertThat(firstname).isEqualTo("Nanda").isNotEmpty().isNotBlank();

    }

    @Test
    public void deleteBookingID(){

        r = RestAssured.given();


        r.baseUri("https://restful-booker.herokuapp.com");

        r.basePath("/booking/"+bookingId);
        r.contentType(ContentType.JSON);
       r.cookie("token", token).log().all();


      //  r.auth().basic("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");


        resp = r.when().delete();
        assertThat(resp).as("Created");
        vr = resp.then().log().all();
        vr.log().all().statusCode(201);

    }

    @Test
    public void getdeletedBookingID(){
        r = RestAssured.given();


        r.baseUri("https://restful-booker.herokuapp.com");

        r.basePath("/booking/"+bookingId);
        r.contentType(ContentType.JSON);





        resp = r.when().get();

        vr = resp.then().log().all();
        assertThat(resp).as("Not Found");
        vr.log().all().statusCode(404);
    }


}
