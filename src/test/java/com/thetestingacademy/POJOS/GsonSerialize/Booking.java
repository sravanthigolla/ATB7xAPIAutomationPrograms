package com.thetestingacademy.POJOS.GsonSerialize;

public class Booking {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;

    private BookingDates bookingdates;
    private String additionalneeds;
// use json to pojo convertor- jsonschema2pojo.org
    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }



    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }
}
