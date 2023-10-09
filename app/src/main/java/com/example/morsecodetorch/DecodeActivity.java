package com.example.morsecodetorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DecodeActivity extends MainActivity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }
}