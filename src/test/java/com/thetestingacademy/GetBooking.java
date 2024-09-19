package com.thetestingacademy;

import io.restassured.RestAssured;

public class GetBooking {

    public static void main(String[] args) {

        //System.out.println("Hello! World");

       // given() - url, headers, payload
        //when() - http methods - get, post , patch, put, delete
        //then() - verify the response
//BDD style
        test1();
        test2();

            }
  private static void test1()
  {
      RestAssured
              .given()
              .baseUri("https://restful-booker.herokuapp.com")
              .basePath("/booking/1")
              .when()
              .get()
              .then().log().all()
              .statusCode(200);
  }

    private static void test2()
    {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/2")
                .when()
                .get()
                .then().log().all()
                .statusCode(200);
    }



}
