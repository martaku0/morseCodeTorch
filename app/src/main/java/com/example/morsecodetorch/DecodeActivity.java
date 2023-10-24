package com.example.morsecodetorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;

public class DecodeActivity extends MainActivity {

    private Button back;
    private Button clearAll;
    private EditText decryptedMessage;
    private EditText encryptedMessage;
    private Button pasteText;
    private Button insertLong;
    private Button insertShort;
    private Button insertSpace;
    private Button decode;
    private Button endChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        back = findViewById(R.id.back);
        clearAll = findViewById(R.id.clearAll);
        decryptedMessage = findViewById(R.id.decryptedMessage);
        encryptedMessage = findViewById(R.id.encryptedMessage);
        pasteText = findViewById(R.id.pasteText);
        insertLong = findViewById(R.id.insertLong);
        insertShort = findViewById(R.id.insertShort);
        insertSpace = findViewById(R.id.insertSpace);
        decode = findViewById(R.id.decode);
        endChar =findViewById(R.id.endChar);

        back.setOnClickListener(view -> goBack());

        clearAll.setOnClickListener(view -> clear(decryptedMessage, encryptedMessage));

        pasteText.setOnClickListener(view -> paste(encryptedMessage));

        decryptedMessage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                copy(decryptedMessage);
                return true;
            }
        });

        insertLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(encryptedMessage.getText());
                temp += "—";
                encryptedMessage.setText(temp);
            }
        });

        insertShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(encryptedMessage.getText());
                temp += "•";
                encryptedMessage.setText(temp);
            }
        });

        insertSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(encryptedMessage.getText());
                temp += " | ";
                encryptedMessage.setText(temp);
            }
        });

        endChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(encryptedMessage.getText());
                temp += " ";
                encryptedMessage.setText(temp);
            }
        });

        decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encodedText = String.valueOf(encryptedMessage.getText());
                String decodedText = "";
                String[] cArr = encodedText.split(" ");
                for(String s : cArr){
                    Log.d("test", s);
                    if(s.equals("|")){
                        decodedText += " ";
                    }
                    else{
                        int inx = Arrays.asList(morseCode).indexOf(s);
                        if(inx > -1){
                            decodedText += alphabet[inx].toLowerCase();
                        }
                        else{
                            decodedText += s;
                        }
                    }
                }
                decryptedMessage.setText(decodedText);
            }
        });
    }
}