package com.example.dormitoryapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    ProgressDialog progressDialog;


    String ridData, et_r_nameData, et_r_telData, et_r_idcardData, et_r_addData, et_r_emailData, et_usernameData;

    String leases_idData, room_idData, leases_date, expires_dateData, l_c_eData, l_c_wData, l_rentData;

    EditText et_r_name, et_r_tel, et_r_idcard, et_r_add, et_r_email, et_username;

    String HttpUrl = URLs.URL_UPDATE_RENTER;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public void GetValueFromEditText() {
        et_r_nameData = et_r_name.getText().toString().trim();
        et_r_telData = et_r_tel.getText().toString().trim();
        et_r_idcardData = et_r_idcard.getText().toString().trim();
        et_r_addData = et_r_add.getText().toString().trim();
        et_r_emailData = et_r_email.getText().toString().trim();
        et_usernameData = et_username.getText().toString().trim();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        Button btnEditProfile;
        User user = SharedPrefManager.getInstance(getContext()).getUser();

        et_r_name = root.findViewById(R.id.et_r_name);
        et_r_tel = root.findViewById(R.id.et_r_tel);
        et_r_idcard = root.findViewById(R.id.et_r_idcard);
        et_r_add = root.findViewById(R.id.et_r_add);
        et_r_email = root.findViewById(R.id.et_r_email);
        et_username = root.findViewById(R.id.et_username);

        // Assigning ID's to Button.
        btnEditProfile = root.findViewById(R.id.btnEditProfile);

        ridData = user.getRid();
        et_r_name.setText(String.valueOf(user.getR_name()));
        et_r_tel.setText(String.valueOf(user.getR_tel()));
        et_r_idcard.setText(String.valueOf(user.getR_idcard()));
        et_r_add.setText(String.valueOf(user.getR_add()));
        et_r_email.setText(String.valueOf(user.getR_email()));
        et_username.setText(String.valueOf(user.getUsername()));

        leases_idData = user.getLeases_id();
        room_idData = user.getRoom_id();
        leases_date = user.getLeases_date();
        expires_dateData = user.getExpires_date();
        l_c_eData = user.getL_c_e();
        l_c_wData = user.getL_c_w();
        l_rentData = user.getL_rent();

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetValueFromEditText();

//        Log.d("et_name",et_u_nameData_req);
// Showing progress dialog at user registration time.
//                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
//                progressDialog.show();
// Calling method to get value from EditText.
                GetValueFromEditText();
// Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                Toast.makeText(getActivity(), ServerResponse, Toast.LENGTH_LONG).show();

                                //creating a new user object
                                User user = new User(
                                        ridData,
                                        et_r_nameData,
                                        et_r_telData,
                                        et_r_idcardData,
                                        et_r_addData,
                                        et_r_emailData,
                                        et_usernameData,
                                        leases_idData,
                                        room_idData,
                                        leases_date,
                                        expires_dateData,
                                        l_c_eData,
                                        l_c_wData,
                                        l_rentData
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getActivity()).userLogin(user);
                                //starting the profile activity

                                Navigation.findNavController(v).navigate(R.id.action_editProfileFragment_to_profileFragment);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
// Hiding the progress dialog after all task complete.
//                        progressDialog.dismiss();
// Showing error message if something goes wrong.
                        Toast.makeText(getActivity(), volleyError.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();
                        // Adding All values to Params.
                        params.put("username", et_usernameData);
                        params.put("r_name", et_r_nameData);
                        params.put("r_tel", et_r_telData);
                        params.put("r_idcard", et_r_idcardData);
                        params.put("r_add", et_r_addData);
                        params.put("r_email", et_r_emailData);
                        params.put("rid", ridData);
                        return params;
                    }
                };
// Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
// Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);
            }
        });

        return root;

    }
}