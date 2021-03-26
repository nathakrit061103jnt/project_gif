package com.example.dormitoryapp;

import java.io.Serializable;

public class Invoice  implements Serializable {
    private String invoice_id;
    private String aid;
    private String invoice_date;
    private String meters_wnew;
    private String meters_lnew;
    private String water_unit;
    private String light_unit;
    private String total_wprice;
    private String total_lprice;
    private String net_total;
    private String Invoice_status;
    private String leases_id;
    private String invoice_month;
    private String pay_id;

    public Invoice(
            String invoice_id ,
            String aid ,
            String invoice_date,
            String meters_wnew,
            String meters_lnew,
            String water_unit,
            String light_unit,
            String total_wprice,
            String total_lprice,
            String net_total,
            String Invoice_status,
            String leases_id,
            String invoice_month,
            String pay_id) {


        this.invoice_id = invoice_id;
        this.aid = aid;
        this.invoice_date = invoice_date;
        this.meters_wnew = meters_wnew;
        this.meters_lnew = meters_lnew;
        this.water_unit = water_unit;
        this.light_unit = light_unit;
        this.total_wprice = total_wprice;
        this.total_lprice = total_lprice;
        this.net_total = net_total;
        this.Invoice_status = Invoice_status;
        this.leases_id = leases_id;
        this.invoice_month = invoice_month;
        this.pay_id = pay_id;

    }

    // Getter Methods

    public String getInvoice_id() {
        return invoice_id;
    }

    public String getAid() {
        return aid;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public String getMeters_wnew() {
        return meters_wnew;
    }

    public String getMeters_lnew() {
        return meters_lnew;
    }

    public String getWater_unit() {
        return water_unit;
    }

    public String getLight_unit() {
        return light_unit;
    }

    public String getTotal_wprice() {
        return total_wprice;
    }

    public String getTotal_lprice() {
        return total_lprice;
    }

    public String getNet_total() {
        return net_total;
    }

    public String getInvoice_status() {
        return Invoice_status;
    }

    public String getLeases_id() {
        return leases_id;
    }

    public String getInvoice_month() {
        return invoice_month;
    }

    public String getPay_id() {
        return pay_id;
    }

    // Setter Methods

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public void setMeters_wnew(String meters_wnew) {
        this.meters_wnew = meters_wnew;
    }

    public void setMeters_lnew(String meters_lnew) {
        this.meters_lnew = meters_lnew;
    }

    public void setWater_unit(String water_unit) {
        this.water_unit = water_unit;
    }

    public void setLight_unit(String light_unit) {
        this.light_unit = light_unit;
    }

    public void setTotal_wprice(String total_wprice) {
        this.total_wprice = total_wprice;
    }

    public void setTotal_lprice(String total_lprice) {
        this.total_lprice = total_lprice;
    }

    public void setNet_total(String net_total) {
        this.net_total = net_total;
    }

    public void setInvoice_status(String Invoice_status) {
        this.Invoice_status = Invoice_status;
    }

    public void setLeases_id(String leases_id) {
        this.leases_id = leases_id;
    }

    public void setInvoice_month(String invoice_month) {
        this.invoice_month = invoice_month;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }
}
