package com.example.dormitoryapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private LayoutInflater inflater;
    private ArrayList<Invoice> invoicesArrayList;

    private View.OnClickListener onItemClickListener;
    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    //Constructor method
    public InvoiceAdapter(Context ctx, ArrayList<Invoice> invoiceDataArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.invoicesArrayList = invoiceDataArrayList;
    }

    class InvoiceViewHolder extends RecyclerView.ViewHolder {
        //ประกาศชื)อตัวแปรอ้างอิงถึง TextView ที)อยู่บน Layout
        TextView txt_invoice_id,txt_invoice_month,txt_net_total,txt_Invoice_status;

        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
//อ้างอิงถึงตัวแปรแต่ละตัวไปยัง TextView ที)อยู่บน Layout
            txt_invoice_id = itemView.findViewById(R.id.txt_invoice_id);
            txt_invoice_month = itemView.findViewById(R.id.txt_invoice_month);
            txt_net_total = itemView.findViewById(R.id.txt_net_total);
            txt_Invoice_status = itemView.findViewById(R.id.txt_Invoice_status);

            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);

        }
    }

    @NonNull
    @Override
    public InvoiceAdapter.InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //กําหนด Layout ที)ใช้สําหรับแสดงข้อมูลแต่ละรายการบน RecycleView ในที)นีBคือ recycleview_item
        View view = inflater.inflate(R.layout.invoices_item,
                parent, false);
        InvoiceViewHolder holder = new InvoiceViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.InvoiceViewHolder holder, int position) {
//นาข้อมลูแต่ละฟิ วด์ไปแสดงบน TextView แต่ละตัว
        //String invoice_id = invoicesArrayList.get(position).getInvoice_id();
        String txt_invoice_month = invoicesArrayList.get(position).getInvoice_month();
        String txt_net_total = invoicesArrayList.get(position).getNet_total();
        String txt_Invoice_status = invoicesArrayList.get(position).getInvoice_status();

       // holder.txt_invoice_id.setText(invoice_id);
        holder.txt_invoice_month.setText(txt_invoice_month);
        holder.txt_net_total.setText(txt_net_total+"  บาท");

        int Invoice_statusNews= Integer.parseInt(txt_Invoice_status);

        if(Invoice_statusNews==0){
            holder.txt_Invoice_status.setTextColor(Color.parseColor("#cb5551"));
            holder.txt_Invoice_status.setText("ยังไม่ชำระค่าเช่า");
        }else{
            holder.txt_Invoice_status.setText("ชำระค่าเช่าเรียบร้อย");
            holder.txt_Invoice_status.setTextColor(Color.parseColor("#72c245"));
        }

        if(Invoice_statusNews==0){
            holder.txt_Invoice_status.setTextColor(Color.parseColor("#E91E63"));
            holder.txt_Invoice_status.setText("ยังไม่ชำระค่าเช่า");
        }else if(Invoice_statusNews==1){
            holder.txt_Invoice_status.setTextColor(Color.parseColor("#FFFF5722"));
            holder.txt_Invoice_status.setText("รอการตรวจสอบ");
        }else{
            holder.txt_Invoice_status.setText("ชำระค่าเช่าเรียบร้อย");
            holder.txt_Invoice_status.setTextColor(Color.parseColor("#72c245"));
        }


    }

    @Override
    public int getItemCount() {
        return invoicesArrayList.size();
    }


}
