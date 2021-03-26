package com.example.dormitoryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeasesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeasesFragment extends Fragment {


    private String ridData;
    User user = SharedPrefManager.getInstance(getContext()).getUser();
    private int leases_id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeasesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeasesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeasesFragment newInstance(String param1, String param2) {
        LeasesFragment fragment = new LeasesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_leases, container, false);

        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()) {

            TextView txt_leases_idData, txt_room_idData, txt_leases_dateData, txt_expires_dateData, txt_l_c_eData,
                    txt_l_c_wData, txt_l_rentData;

            User user = SharedPrefManager.getInstance(getContext()).getUser();

            txt_leases_idData = view.findViewById(R.id.txt_leases_id);
            txt_room_idData = view.findViewById(R.id.txt_room_id);
            txt_leases_dateData = view.findViewById(R.id.txt_leases_date);
            txt_expires_dateData = view.findViewById(R.id.txt_expires_date);
            txt_l_c_eData = view.findViewById(R.id.txt_l_c_e);
            txt_l_c_wData = view.findViewById(R.id.txt_l_c_w);
            txt_l_rentData = view.findViewById(R.id.txt_l_rent);

            txt_leases_idData.setText(String.valueOf(user.getLeases_id()));
            txt_room_idData.setText(String.valueOf(user.getRoom_id()));
            txt_leases_dateData.setText(String.valueOf(user.getLeases_date()));
            txt_expires_dateData.setText(String.valueOf(user.getExpires_date()));
            txt_l_c_eData.setText(user.getL_c_e() + "   บาท/นวย");
            txt_l_c_wData.setText(user.getL_c_w() + "   บาท/หนวย");
            txt_l_rentData.setText(user.getL_rent() + "   บาท/เดือน");


        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

    }

}

