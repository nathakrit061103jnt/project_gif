package com.example.dormitoryapp;

public class Leases {
    private String leases_id;
    private String room_id;
    private String leases_date;
    private String expires_date;
    private String l_c_e;
    private String l_c_w;
    private String l_rent;

    //constructor
    public Leases(String leases_id,
                  String room_id, String leases_date,
                  String expires_date,
                  String l_c_e, String l_c_w, String l_rent) {

        this.leases_id = leases_id;
        this.room_id = room_id;
        this.leases_date = leases_date;
        this.expires_date = expires_date;
        this.l_c_e = l_c_e;
        this.l_c_w = l_c_w;
        this.l_rent = l_rent;

    }

    // Getter Methods
    public String getLeases_id() {
        return leases_id;
    }



    public String getRoom_id() {
        return room_id;
    }

    public String getLeases_date() {
        return leases_date;
    }

    public String getExpires_date() {
        return expires_date;
    }

    public String getL_c_e() {
        return l_c_e;
    }

    public String getL_c_w() {
        return l_c_w;
    }

    public String getL_rent() {
        return l_rent;
    }

    // Setter Methods

    public void setLeases_id(String leases_id) {
        this.leases_id = leases_id;
    }



    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public void setLeases_date(String leases_date) {
        this.leases_date = leases_date;
    }

    public void setExpires_date(String expires_date) {
        this.expires_date = expires_date;
    }



    public void setL_c_e(String l_c_e) {
        this.l_c_e = l_c_e;
    }

    public void setL_c_w(String l_c_w) {
        this.l_c_w = l_c_w;
    }

    public void setL_rent(String l_rent) {
        this.l_rent = l_rent;
    }
}
