package com.thetestingacademy.POJOS.GsonSerialize;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class gsonSerialize {

    RequestSpecification r;
    ValidatableResponse vr;
    Response resp;

    @Test
    public void Booking() {

        Booking b = new Booking();
        b.setFirstname("Jim");
        b.setLastname("Brown");
        b.setTotalprice(111);
        b.setDepositpaid(true);


        BookingDates bd = new BookingDates();
        bd.setCheckin("2018-01-01");
        bd.setCheckout("2019-01-01");

        b.setBookingdates(bd);

        b.setAdditionalneeds("Breakfast");

        System.out.println(b);
        //java object to JSOn string- serialization

        Gson gson = new Gson();
        String payload_create = gson.toJson(b);
        System.out.println(payload_create);


        r = RestAssured.given();


        r.baseUri("https://restful-booker.herokuapp.com");

        r.basePath("/booking");
        r.contentType(ContentType.JSON);


        r.body(payload_create);
//
        resp = r.when().log().all().post();

        String respstring = resp.asString();
        System.out.println(respstring);


        vr = resp.then().log().all();
        vr.statusCode(200);

        //deserialization - parse
/*
        BookingResponse br = gson.fromJson(respstring, BookingResponse.class);
        System.out.println(br.getBookingid());
        System.out.println(br.getBooking());

 */
    }

    }


