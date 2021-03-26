package com.example.dormitoryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvoiceFragment extends Fragment {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private RecyclerView rvInvoices;
    private InvoiceAdapter invoicesAdapter;
    private ArrayList<Invoice> listData;

    User user = SharedPrefManager.getInstance(getContext()).getUser();
    String leases_id = user.getLeases_id();
    String jsonURL = URLs.ROOT_URL + "get_invoice_id.php?leases_id=" + leases_id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvoiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvoiceFragment newInstance(String param1, String param2) {
        InvoiceFragment fragment = new InvoiceFragment();
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
        View view = inflater.inflate(R.layout.fragment_invoice, container, false);

        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()) {

            rvInvoices = view.findViewById(R.id.rvInvoices);
            rvInvoices.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            rvInvoices.setLayoutManager(llm);
//เรียกใช้เมธอด loadMovieData ที@สร้างก่อนหน้านี=

            loadInvoiceData();

//            Log.e("URL : ",jsonURL);

        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//เมื@อผู้ใช้กดปุ่ ม แก้ไขข้อมูลจะย้อนกลับมาหน้านี=ซึ@งจะมีresultCode = -1
        if (requestCode == 1 && resultCode == -1) {
            loadInvoiceData();
            invoicesAdapter.notifyDataSetChanged();
        }
    }

    public void loadInvoiceData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("data");
                            listData = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject dataObj = array.getJSONObject(i);
                                Invoice itemData = new Invoice(
                                        dataObj.getString("invoice_id"),
                                        dataObj.getString("aid"),
                                        dataObj.getString("invoice_date"),
                                        dataObj.getString("meters_wnew"),
                                        dataObj.getString("meters_lnew"),
                                        dataObj.getString("water_unit"),
                                        dataObj.getString("light_unit"),
                                        dataObj.getString("total_wprice"),
                                        dataObj.getString("total_lprice"),
                                        dataObj.getString("net_total"),
                                        dataObj.getString("Invoice_status"),
                                        dataObj.getString("leases_id"),
                                        dataObj.getString("invoice_month"),
                                        dataObj.getString("pay_id")
                                );
                                listData.add(itemData);
                            } //end for
                            invoicesAdapter = new InvoiceAdapter(getActivity(), listData);
                            rvInvoices.setAdapter(invoicesAdapter);

                            invoicesAdapter.setItemClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                    int position = viewHolder.getAdapterPosition();
                                    Intent data = new Intent(view.getContext(), InvioceActivity.class);
                                    Invoice invoice = listData.get(position);
                                    data.putExtra("invoiceData", invoice);
                                    startActivityForResult(data, 1);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } //end try
                    } //end onResponse
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
            } //end onErrorResponse
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    } //end loadMovieData method

}