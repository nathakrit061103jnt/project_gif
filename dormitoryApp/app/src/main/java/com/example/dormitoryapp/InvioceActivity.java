package com.example.dormitoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InvioceActivity extends AppCompatActivity {

    Invoice invoiceData;
    private TextView txt_invoice_id,txt_meters_wnew,txt_meters_lnew,txt_water_unit,txt_light_unit,
                     txt_total_wprice,txt_total_lprice,txt_net_total,txt_Invoice_status,txt_leases_id,
                     txt_invoice_month;

    private View.OnClickListener onItemClickListener;
    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invioce);

        Button buttonBtn = (Button) findViewById(R.id.upload);
        buttonBtn.setVisibility(View.VISIBLE);

      //  buttonH.setVisibility(View.GONE);
//        buttonH.setVisibility(View.VISIBLE);

        txt_invoice_id = findViewById(R.id.txt_invoice_id);
        txt_Invoice_status= findViewById(R.id.txt_Invoice_status);

        txt_meters_wnew = findViewById(R.id.txt_meters_wnew);
        txt_meters_lnew= findViewById(R.id.txt_meters_lnew);
        txt_water_unit = findViewById(R.id.txt_water_unit);
        txt_light_unit= findViewById(R.id.txt_light_unit);
        txt_total_wprice = findViewById(R.id.txt_total_wprice);
        txt_total_lprice= findViewById(R.id.txt_total_lprice);
        txt_net_total = findViewById(R.id.txt_net_total);
        txt_leases_id= findViewById(R.id.txt_leases_id);
        txt_invoice_month = findViewById(R.id.txt_invoice_month);


//รับข้อมูล moviedata ที8ส่งมาจากหน้า ListMoviesFragment
        invoiceData = (Invoice) getIntent().getSerializableExtra("invoiceData");
//นําข้อมูลไปแสดงบน EditText แต่ละอัน
        txt_invoice_id.setText(invoiceData.getInvoice_id());
        txt_meters_wnew.setText(invoiceData.getMeters_wnew() + "  หน่วย");
        txt_meters_lnew.setText(invoiceData.getMeters_lnew()+ "  หน่วย");
        txt_water_unit.setText(invoiceData.getWater_unit()+ "  หน่วย");
        txt_light_unit.setText(invoiceData.getLight_unit()+ "  หน่วย");
        txt_total_wprice.setText(invoiceData.getTotal_wprice()+ "  บาท");
        txt_total_lprice.setText(invoiceData.getTotal_lprice()+ "  บาท");
        txt_net_total.setText(invoiceData.getNet_total()+ "  บาท");
        txt_leases_id.setText(invoiceData.getLeases_id());
        txt_invoice_month.setText(invoiceData.getInvoice_month());

        buttonBtn.setOnClickListener(new View.OnClickListener(){
            // เขียน Method ให้กับปุ่ม
            @Override
            public void onClick(View v) {
                // ใช้ Class Navigation เพื่อใช้ในการเปลี่ยนหน้า เเละอ้างไปยังเส้น Destination ที่จะเปลี่ยนหน้า
                Intent data = new Intent( getApplicationContext(), PaymentActivity.class);

                Invoice invoice =  new Invoice(
                        invoiceData.getInvoice_id() ,
                        invoiceData.getAid()  ,
                        invoiceData.getInvoice_date(),
                        invoiceData.getMeters_wnew(),
                        invoiceData .getMeters_lnew(),
                        invoiceData .getWater_unit(),
                        invoiceData .getLight_unit(),
                        invoiceData .getTotal_wprice()  ,
                        invoiceData .getTotal_lprice(),
                        invoiceData .getNet_total(),
                        invoiceData .getInvoice_status(),
                        invoiceData .getLeases_id(),
                        invoiceData .getInvoice_month(),
                        invoiceData .getPay_id()
                );

                data.putExtra("invoiceData", invoice);
                startActivityForResult(data, 1);
            }
        });

        int Invoice_statusNews= Integer.parseInt(invoiceData.getInvoice_status());

        if(Invoice_statusNews==0){
            txt_Invoice_status.setTextColor(Color.parseColor("#E91E63"));
            txt_Invoice_status.setText("ยังไม่ชำระค่าเช่า");
        }else if(Invoice_statusNews==1){
            txt_Invoice_status.setText("รอการตรวจสอบ");
            txt_Invoice_status.setTextColor(Color.parseColor("#FFFF5722"));
            buttonBtn.setVisibility(View.GONE);
        }else{
            txt_Invoice_status.setText("ชำระค่าเช่าเรียบร้อย");
            txt_Invoice_status.setTextColor(Color.parseColor("#72c245"));
            buttonBtn.setVisibility(View.GONE);
        }

    }
}