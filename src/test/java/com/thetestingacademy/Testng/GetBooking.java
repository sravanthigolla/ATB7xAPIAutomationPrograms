package com.thetestingacademy.Testng;

import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;

import org.testng.annotations.*;
import io.qameta.allure.Severity;
public class GetBooking {

    @BeforeSuite

    void f1()
    {
        System.out.println("BeforeSuite");
    }

    @BeforeTest

    void f2()
    {
        System.out.println("BeforeTest");
    }

    @BeforeClass

    void f3()
    {
        System.out.println("BeforeClass");
    }

    @BeforeMethod

    void f4()
    {
        System.out.println("BeforeMethod");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Get Booking")
    @Test

    public void test_get(){
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/1")
                .when()
                .get()
                .then().log().all()
                .statusCode(200);
    }

    @AfterMethod
    void f5()
    {
        System.out.println("AfterMethod");
    }

    @AfterClass
    void f6()
    {
        System.out.println("AfterClass");
    }

    @AfterTest
    void f7()
    {
        System.out.println("AfterTest");
    }

    @AfterSuite
    void f8()
    {
        System.out.println("AfterSuite-close everything, delete all the temp files");
    }

}
