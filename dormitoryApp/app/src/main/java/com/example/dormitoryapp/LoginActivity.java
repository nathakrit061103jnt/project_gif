package com.example.dormitoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;


    // Creating Volley RequestQueue.
    RequestQueue requestQueue;
    // Create string variable to hold the EditText Value.
    String etUsernameData, etPasswordData;
    // Creating Progress dialog.
    ProgressDialog progressDialog;
    // Storing server url into String variable.
    String HttpUrl = URLs.URL_LOGIN;

    // Creating method to get value from EditText.
    public void GetValueFromEditText() {
        etUsernameData = etUsername.getText().toString().trim();
        etPasswordData = etPassword.getText().toString().trim();

        //validating inputs
        if (TextUtils.isEmpty(etUsernameData)) {
            etUsername.setError("Please enter your username");
            etUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(etPasswordData)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assigning ID's to EditText.
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        // Assigning ID's to Button.
        btnLogin = findViewById(R.id.btnLogin);


        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


    }

    private void userLogin() {

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();
        // Calling method to get value from EditText.
        GetValueFromEditText();
        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(ServerResponse);
                            // Showing response message coming from server.

                            int status = obj.getInt("status");

                            if (status == 200) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                String rid = obj.getString("rid");
                                String r_name, r_tel, r_idcard, r_add, r_email, username;
                                r_name = obj.getString("r_name");
                                r_tel = obj.getString("r_tel");
                                r_idcard = obj.getString("r_idcard");
                                r_add = obj.getString("r_add");
                                r_email = obj.getString("r_email");
                                username = obj.getString("username");

                                String leases_id, room_id, leases_date, expires_date, l_c_e, l_c_w, l_rent;

                                leases_id = obj.getString("leases_id");
                                room_id = obj.getString("room_id");
                                leases_date = obj.getString("leases_date");
                                expires_date = obj.getString("expires_date");
                                l_c_e = obj.getString("l_c_e");
                                l_c_w = obj.getString("l_c_w");
                                l_rent = obj.getString("l_rent");

                                //creating a new user object
                                User user = new User(
                                        rid,
                                        r_name,
                                        r_tel,
                                        r_idcard,
                                        r_add,
                                        r_email,
                                        username,
                                        leases_id, room_id, leases_date, expires_date, l_c_e, l_c_w, l_rent
                                );

//                                Leases leases = new Leases(
//                                        leases_id, room_id, leases_date, expires_date, l_c_e, l_c_w, l_rent
//                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                //starting the profile activity
                                finish();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                btnLogin.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "username หรือ password ไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            btnLogin.setVisibility(View.VISIBLE);
                            StringWriter stringWriter = new StringWriter();
                            ex.printStackTrace(new PrintWriter(stringWriter));
                            Log.e("exception ::: ", stringWriter.toString());
                            Toast.makeText(getApplicationContext(),  ex.toString() , Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        btnLogin.setVisibility(View.VISIBLE);
                        // Showing error message if something goes wrong.
//                                Toast.makeText(getApplicationContext(), volleyError.toString(),
//                                        Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "username หรือ password ไม่ถูกต้อง", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                params.put("username", etUsernameData);
                params.put("password", etPasswordData);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }
}