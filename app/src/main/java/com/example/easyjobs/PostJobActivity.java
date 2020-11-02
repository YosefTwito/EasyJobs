package com.example.easyjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PostJobActivity extends AppCompatActivity {

    private ImageView backBPJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        backBPJ = findViewById(R.id.back_post_job);
        backBPJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostJobActivity.super.onBackPressed();
            }
        });
    }
}