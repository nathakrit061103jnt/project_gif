package com.example.dormitoryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_ID = "key_rid";
    private static final String KEY_NAME = "key_r_name";
    private static final String KEY_TEL = "key_r_tel";
    private static final String KEY_ID_CARD = "r_r_idcard";
    private static final String KEY_ADDRESS = "r_add";
    private static final String KEY_EMAIL = "r_email";
    private static final String KEY_USERNAME = "username";

    private static final String LEASES_ID = "leases_id";
    private static final String KEY_ROOM_ID = "room_id";
    private static final String KEY_LEASES_DATE = "leases_date";
    private static final String KEY_EXPIRES_DATE = "expires_date";
    private static final String KEY_L_C_E = "l_c_e";
    private static final String KEY_L_C_W = "l_c_w";
    private static final String KEY_L_RENT = "l_rent";

    private static  SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized  SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new  SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User user ) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, user.getRid());
        editor.putString(KEY_NAME, user.getR_name());
        editor.putString(KEY_TEL, user.getR_tel());
        editor.putString(KEY_ID_CARD, user.getR_idcard());
        editor.putString(KEY_ADDRESS, user.getR_add());
        editor.putString(KEY_EMAIL, user.getR_email());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(LEASES_ID, user.getLeases_id());
        editor.putString(KEY_ROOM_ID, user.getRoom_id());
        editor.putString(KEY_LEASES_DATE, user.getLeases_date());
        editor.putString(KEY_EXPIRES_DATE, user.getExpires_date());
        editor.putString(KEY_L_C_E, user.getL_c_e());
        editor.putString(KEY_L_C_W, user.getL_c_w());
        editor.putString(KEY_L_RENT, user.getL_rent());

        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_NAME,null),
                sharedPreferences.getString(KEY_TEL, null),
                sharedPreferences.getString(KEY_ID_CARD, null),
                sharedPreferences.getString(KEY_ADDRESS, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_USERNAME, null),

                sharedPreferences.getString(LEASES_ID, null),
                sharedPreferences.getString(KEY_ROOM_ID,null),
                sharedPreferences.getString(KEY_LEASES_DATE, null),
                sharedPreferences.getString(KEY_EXPIRES_DATE, null),
                sharedPreferences.getString(KEY_L_C_E, null),
                sharedPreferences.getString(KEY_L_C_W, null),
                sharedPreferences.getString(KEY_L_RENT, null)

        );
    }

    public Leases getLeases() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Leases(
                sharedPreferences.getString(LEASES_ID, null),
                sharedPreferences.getString(KEY_ROOM_ID,null),
                sharedPreferences.getString(KEY_LEASES_DATE, null),
                sharedPreferences.getString(KEY_EXPIRES_DATE, null),
                sharedPreferences.getString(KEY_L_C_E, null),
                sharedPreferences.getString(KEY_L_C_W, null),
                sharedPreferences.getString(KEY_L_RENT, null)
                );
    }


    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }

}
