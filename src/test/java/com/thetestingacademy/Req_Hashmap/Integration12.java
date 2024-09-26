package com.thetestingacademy.Req_Hashmap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Integration12 {

    RequestSpecification r;
    ValidatableResponse vr;
    Response resp;
    String token;
    Integer bookingId;


    @BeforeTest
    public String getToken(){

     /*   String payload = "{\n" +
                "            \"username\":\"admin\",\n" +
                "                \"password\":\"password123\"\n" +
                "        }";
        */

        Map<String,Object> tokenmap = new LinkedHashMap<>();
        tokenmap.put("username", "admin");
        tokenmap.put("password","password123");

        System.out.println(tokenmap);

        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(tokenmap);
        Response resp = r.when().post();
        System.out.println(resp.asString());
        JsonPath jsonpath = new JsonPath(resp.asString());
        ValidatableResponse vr = resp.then();
        vr.log().all().statusCode(200);
        //Use json path finder to find json path
        token = jsonpath.getString("token");
        assertThat(token).isNotNull();
        System.out.println(token);
        return token;
    }


    public Integer getBookingID(){


/*
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
*/
        Map<String,Object> bookingmap = new LinkedHashMap<>();
        bookingmap.put("firstname", "Jam");
        bookingmap.put("lastname","Brown");
        bookingmap.put("totalprice","111");
        bookingmap.put("depositpaid","true");

        Map<String,Object> bookingdatesmap = new LinkedHashMap<>();
        bookingdatesmap.put("checkin", "2019-01-01");
        bookingdatesmap.put("checkout","2019-01-01");

        bookingmap.put("bookingdates", bookingdatesmap);

        bookingmap.put("additionalneeds","Breakfast");

        System.out.println(bookingmap);

        r = RestAssured.given();


        r.baseUri("https://restful-booker.herokuapp.com");
//        r.basePath("/auth");
        r.basePath("/booking/");
        r.contentType(ContentType.JSON);
        //r.accept();


        r.body(bookingmap);
//
        resp = r.when().post();
        JsonPath jsonpath1 = new JsonPath(resp.asString());

        vr = resp.then();
        vr.statusCode(200);
        bookingId = jsonpath1.getInt("bookingid");
        String firstname = jsonpath1.getString("booking.firstname");
        assertThat(bookingId).isNotNull().isNotZero().isPositive();

        System.out.println(firstname);

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

        r.body(payloadpatch);
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
