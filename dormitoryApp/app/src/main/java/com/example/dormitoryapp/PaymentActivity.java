package com.example.dormitoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    Invoice invoiceData;
    private TextView chooseImg;
    private TextView txt_invoice_id, txt_meters_wnew, txt_meters_lnew, txt_water_unit, txt_light_unit,
            txt_total_wprice, txt_total_lprice, txt_net_total, txt_Invoice_status, txt_leases_id,
            txt_invoice_month;

    private TextView txt_r_name, txt_room_id;

    ImageView image;
    TextView choose;
    Button upload;
    int PICK_IMAGE_REQUEST = 111;
    String URL = URLs.URL_PAYMENT;
    Bitmap bitmap;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        txt_invoice_id = findViewById(R.id.txt_invoice_id);

        txt_total_wprice = findViewById(R.id.txt_total_wprice);
        txt_total_lprice = findViewById(R.id.txt_total_lprice);
        txt_net_total = findViewById(R.id.txt_net_total);
        txt_leases_id = findViewById(R.id.txt_leases_id);
        txt_invoice_month = findViewById(R.id.txt_invoice_month);

        txt_r_name = findViewById(R.id.txt_r_name);
        txt_room_id = findViewById(R.id.txt_room_id);

        //รับข้อมูล moviedata ที8ส่งมาจากหน้า ListMoviesFragment
        invoiceData = (Invoice) getIntent().getSerializableExtra("invoiceData");
//นําข้อมูลไปแสดงบน EditText แต่ละอัน
        txt_invoice_id.setText(invoiceData.getInvoice_id());

        txt_total_wprice.setText(invoiceData.getTotal_wprice() + "  บาท");
        txt_total_lprice.setText(invoiceData.getTotal_lprice() + "  บาท");
        txt_net_total.setText(invoiceData.getNet_total() + "  บาท");
        txt_leases_id.setText(invoiceData.getLeases_id());
        txt_invoice_month.setText(invoiceData.getInvoice_month());


        txt_r_name.setText(user.getR_name());
        txt_room_id.setText(String.valueOf(user.getRoom_id()));

        image = findViewById(R.id.image);
        choose = findViewById(R.id.choose);
        upload = findViewById(R.id.upload);
        chooseImg = findViewById(R.id.chooseImg);

        //opening image chooser option
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                progressDialog = new ProgressDialog(getApplicationContext());
//                progressDialog.setMessage("Uploading, please wait...");
//                progressDialog.show();

                //converting image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                //sending image to server
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(ServerResponse);
                            // Showing response message coming from server.

                            int status = obj.getInt("status");

                            if (status == 200) {
//                                                    progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();

//

                            } else {
//                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "แจ้งชำระบิลเรียบร้อย", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            StringWriter stringWriter = new StringWriter();
                            ex.printStackTrace(new PrintWriter(stringWriter));
                            Log.e("exception ::: ", stringWriter.toString());
                            Toast.makeText(getApplicationContext(),  ex.toString() , Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "แจ้งชำระบิลเรียบร้อย"  , Toast.LENGTH_LONG).show();
                        ;
                    }
                })
                {
                    //adding parameters to send
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("image", imageString);
                        parameters.put("room_id", user.getRoom_id());
                        parameters.put("pay_total", invoiceData.getNet_total());
                        parameters.put("invoice_id", invoiceData.getInvoice_id());
                        parameters.put("leases_id", invoiceData.getLeases_id());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(getApplicationContext());
                rQueue.add(request);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                chooseImg.setVisibility(View.GONE);
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting image to ImageView
                image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



