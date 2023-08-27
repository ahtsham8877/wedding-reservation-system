package com.fyp.e_laboratory.Model;

public class Hotellist {
    int image;
    String hotelname;

    public Hotellist() {
    }

    public Hotellist(int image, String hotelname) {
        this.image = image;
        this.hotelname = hotelname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }
}
