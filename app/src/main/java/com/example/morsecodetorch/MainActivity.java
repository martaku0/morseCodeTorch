package com.example.morsecodetorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button startEncode;
    private Button startDecode;
    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

    public static final Map<String, Character> morseAlphabet = new HashMap<>();

    static {
        morseAlphabet.put(".-", 'A');
        morseAlphabet.put("-...", 'B');
        morseAlphabet.put("-.-.", 'C');
        morseAlphabet.put("-..", 'D');
        morseAlphabet.put(".", 'E');
        morseAlphabet.put("..-.", 'F');
        morseAlphabet.put("--.", 'G');
        morseAlphabet.put("....", 'H');
        morseAlphabet.put("..", 'I');
        morseAlphabet.put(".---", 'J');
        morseAlphabet.put("-.-", 'K');
        morseAlphabet.put(".-..", 'L');
        morseAlphabet.put("--", 'M');
        morseAlphabet.put("-.", 'N');
        morseAlphabet.put("---", 'O');
        morseAlphabet.put(".--.", 'P');
        morseAlphabet.put("--.-", 'Q');
        morseAlphabet.put(".-.", 'R');
        morseAlphabet.put("...", 'S');
        morseAlphabet.put("-", 'T');
        morseAlphabet.put("..-", 'U');
        morseAlphabet.put("...-", 'V');
        morseAlphabet.put(".--", 'W');
        morseAlphabet.put("-..-", 'X');
        morseAlphabet.put("-.--", 'Y');
        morseAlphabet.put("--..", 'Z');
        morseAlphabet.put("-----", '0');
        morseAlphabet.put(".----", '1');
        morseAlphabet.put("..---", '2');
        morseAlphabet.put("...--", '3');
        morseAlphabet.put("....-", '4');
        morseAlphabet.put(".....", '5');
        morseAlphabet.put("-....", '6');
        morseAlphabet.put("--...", '7');
        morseAlphabet.put("---..", '8');
        morseAlphabet.put("----.", '9');
    }


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

    public void clear(EditText eTxt1, EditText eTxt2){
        eTxt1.setText("");
        eTxt2.setText("");
    }

    public void copy(EditText eTxt1){
        String text = String.valueOf(eTxt1.getText());
        clipboard.setText(text);
    }

    public void paste(EditText eTxt1){
        String text = (String) clipboard.getText();
        eTxt1.setText(text);
    }

}