package com.example.morsecodetorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.Arrays;

public class EncodeActivity extends MainActivity {

    private Button back;
    private Button clearAll;
    private EditText plainText;
    private EditText encryptedMessage;
    private Button pasteText;
    private Button encode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);

        back = findViewById(R.id.back);
        clearAll = findViewById(R.id.clearAll);
        plainText = findViewById(R.id.plainText);
        encryptedMessage = findViewById(R.id.encryptedMessage);
        pasteText = findViewById(R.id.pasteText);
        encode = findViewById(R.id.encode);

        back.setOnClickListener(view -> goBack());

        clearAll.setOnClickListener(view -> clear(plainText, encryptedMessage));

        pasteText.setOnClickListener(view -> paste(plainText));

        encryptedMessage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                copy(encryptedMessage);
                return true;
            }
        });

        encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToEncode = String.valueOf(plainText.getText());
                String encodedText = "";
                for (char c: textToEncode.toCharArray()
                     ) {
                    if(c == ' '){
                        encodedText += " | ";
                    }
                    else{
                        c = Character.toUpperCase(c);
                        int inx = Arrays.asList(alphabet).indexOf(String.valueOf(c));
                        if(inx > -1){
                            encodedText += morseCode[inx];
                        }
                        else{
                            encodedText += c;
                        }
                        encodedText += " ";
                    }
                }
                encryptedMessage.setText(encodedText);
                hideKeyboard((Button)view);
            }
        });

    }
}