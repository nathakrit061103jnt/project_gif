package com.example.dormitoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private LayoutInflater inflater;
    private ArrayList<New> newsArrayList;

    //Constructor method
    public NewAdapter(Context ctx, ArrayList<New> newDataArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.newsArrayList = newDataArrayList;
    }

    private View.OnClickListener onItemClickListener;
    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    class NewViewHolder extends RecyclerView.ViewHolder {
        //ประกาศชื)อตัวแปรอ้างอิงถึง TextView ที)อยู่บน Layout
        TextView txt_news_title,txt_news_date;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            //อ้างอิงถึงตัวแปรแต่ละตัวไปยัง TextView ที)อยู่บน Layout
            txt_news_title = itemView.findViewById(R.id.txt_news_title);
            txt_news_date= itemView.findViewById(R.id.txt_news_date);

            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //กําหนด Layout ที)ใช้สําหรับแสดงข้อมูลแต่ละรายการบน RecycleView ในที)นีBคือ recycleview_item
        View view = inflater.inflate(R.layout.news_item,
                parent, false);
        NewViewHolder holder = new NewViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, int position) {
        //นาข้อมลูแต่ละฟิ วด์ไปแสดงบน TextView แต่ละตัว
        String news_title = newsArrayList.get(position).getNews_title();
        holder.txt_news_title.setText(news_title);
        String txt_news_date=newsArrayList.get(position).getNews_date();
        holder.txt_news_date.setText(txt_news_date);
    }


    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }
}
