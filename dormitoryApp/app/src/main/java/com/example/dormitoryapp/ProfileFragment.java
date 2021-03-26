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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()) {

            TextView txt_ridData, txt_r_nameData, r_telData, r_idcardData, txt_r_addData, txt_r_emailData, usernameData,
                    txt_room_id;

            User user = SharedPrefManager.getInstance(getContext()).getUser();

            Button btnEditProfile_to_page = (Button) view.findViewById(R.id.btnEditProfile_to_page);

            // สร้าง Event ให้กับป่ม
            btnEditProfile_to_page.setOnClickListener(new View.OnClickListener(){

                // เขียน Method ให้กับปุ่ม
                @Override
                public void onClick(View v) {
                    // ใช้ Class Navigation เพื่อใช้ในการเปลี่ยนหน้า เเละอ้างไปยังเส้น Destination ที่จะเปลี่ยนหน้า
                    Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_editProfileFragment);
                }
            });

            txt_ridData = view.findViewById(R.id.txt_rid);
            txt_r_nameData = view.findViewById(R.id.txt_r_name);
            r_telData = view.findViewById(R.id.r_tel);
            r_idcardData = view.findViewById(R.id.txt_r_idcard);
            txt_r_addData = view.findViewById(R.id.txt_r_add);
            txt_r_emailData = view.findViewById(R.id.txt_r_email);
            usernameData = view.findViewById(R.id.txt_username);

            txt_room_id= view.findViewById(R.id.txt_room_id);

            txt_ridData.setText(String.valueOf(user.getRid()));
            txt_r_nameData.setText(String.valueOf(user.getR_name()));
            r_telData.setText(String.valueOf(user.getR_tel()));
            r_idcardData.setText(String.valueOf(user.getR_idcard()));
            txt_r_addData.setText(String.valueOf(user.getR_add()));
            txt_r_emailData.setText(String.valueOf(user.getR_email()));
            usernameData.setText(String.valueOf(user.getUsername()));

            txt_room_id.setText(String.valueOf(user.getRoom_id()));

            //สร้างปุ่มขึ้นมา โดยอ้างไปยังไอดีของปุ่ม
            Button buttonLogout = view.findViewById(R.id.buttonLogout);

            // สร้าง Event ให้กับป่ม
            buttonLogout.setOnClickListener(new View.OnClickListener() {

                // เขียน Method ให้กับปุ่ม
                @Override
                public void onClick(View v) {
                    SharedPrefManager.getInstance(getActivity()).logout();
                }
            });

        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

    }
}