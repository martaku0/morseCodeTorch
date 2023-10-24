package com.example.morsecodetorch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button startEncode;
    private Button startDecode;
    public ClipboardManager clipboard;

    public String[] alphabet;
    public String[] morseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clipboard  = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        morseCode = new String[]{
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
                "..-", "...-", ".--", "-..-", "-.--", "--..",
                "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."
        };

        alphabet = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };

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

    public void clear(EditText eTxt1, EditText eTxt2){
        eTxt1.setText("");
        eTxt2.setText("");
    }

    public void copy(EditText eTxt1){
        String text = String.valueOf(eTxt1.getText());
        clipboard.setText(text);
        Toast toast = Toast.makeText(this, "Text copied to clipboard!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void paste(EditText eTxt1){
        String text = (String) clipboard.getText();
        eTxt1.setText(text);
    }

    public void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch(Exception ignored) {
        }
    }

}