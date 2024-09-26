package com.thetestingacademy.POJOS.GsonSerialize;

public class BookingResponse {

    private Integer bookingid;
    private Booking booking;

    public Integer getBookingid() {
        return bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
