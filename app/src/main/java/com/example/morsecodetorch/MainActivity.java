package com.example.morsecodetorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startEncode;
    private Button startDecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startEncode = findViewById(R.id.startEncode);
        startDecode = findViewById(R.id.startDecode);

        startEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEncodeActivity();
            }
        });

        startDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDecodeActivity();
            }
        });

    }

    private void startEncodeActivity(){
        Log.d("check", "startEncodeActivity: ");
        Intent encodeAct = new Intent(this, EncodeActivity.class);
        startActivity(encodeAct);
    }

    private void startDecodeActivity(){
        Log.d("check", "startDecodeActivity: ");
        Intent decodeAct = new Intent(this, DecodeActivity.class);
        startActivity(decodeAct);
    }

    public void goBack(){
        Log.d("check", "goBack: ");
        Intent mainAct = new Intent(this, MainActivity.class);
        startActivity(mainAct);
    }


}