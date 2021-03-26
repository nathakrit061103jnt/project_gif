package com.example.dormitoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    New newData;
    private TextView txt_news_title, txt_description, txt_news_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        txt_news_title = findViewById(R.id.txt_news_title);
        txt_description = findViewById(R.id.txt_description);
        txt_news_date = findViewById(R.id.txt_news_date);

//รับข้อมูล moviedata ที8ส่งมาจากหน้า ListMoviesFragment
        newData = (New) getIntent().getSerializableExtra("newData");
//นําข้อมูลไปแสดงบน EditText แต่ละอัน
        txt_news_title.setText(newData.getNews_title());
        txt_description.setText(newData.getDescription());
        txt_news_date.setText(newData.getNews_date());

    }
}