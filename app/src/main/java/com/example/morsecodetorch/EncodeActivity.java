package com.example.morsecodetorch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.lang.reflect.Array;
import java.util.Arrays;

public class EncodeActivity extends MainActivity {

    private Button back;
    private Button clearAll;
    private EditText plainText;
    private EditText encryptedMessage;
    private Button pasteText;
    private Button encode;
    private Button flashlight;
    private ProgressBar progress;

    private CameraManager cameraManager;
    private String getCameraID;

    private int stop_torch = 1400;
    private int long_torch = 600;
    private int short_torch = 200;
    private boolean flashlightProcessRunning = false;
    private Handler handler = new Handler();


    @Override
    public void onBackPressed() {
        if (flashlightProcessRunning) {
            handler.removeCallbacksAndMessages(null); // Remove all callbacks and messages from the handler
            flashlightProcessRunning = false;

            try {
                cameraManager.setTorchMode(getCameraID, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        super.onBackPressed();
    }

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
        flashlight = findViewById(R.id.flashlight);
        progress = findViewById(R.id.progress);

        back.setOnClickListener(view -> goBack());

        clearAll.setOnClickListener(view -> clear(plainText, encryptedMessage));

        pasteText.setOnClickListener(view -> paste(plainText));

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

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
                        encodedText += "| ";
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

        flashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = String.valueOf(encryptedMessage.getText());

                int max = 0;

                flashlightProcessRunning = true;

                plainText.setEnabled(false);
                encode.setEnabled(false);
                clearAll.setEnabled(false);
                pasteText.setEnabled(false);
                encryptedMessage.setEnabled(false);
                flashlight.setEnabled(false);
                back.setEnabled(false);

                progress.setVisibility(View.VISIBLE);
                progress.setMax(message.length());
                progress.setProgress(1);

                flashMessage(message, 0);
            }

            private void stopFlashlightProcess() {
                progress.setVisibility(View.INVISIBLE);
                handler.removeCallbacksAndMessages(null);
                flashlightProcessRunning = false;

                try {
                    cameraManager.setTorchMode(getCameraID, false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            private void flashMessage(final String message, final int index) {
                if (index < message.length() && flashlightProcessRunning) {
                    char currentChar = message.charAt(index);
                    int duration;

                    switch (currentChar) {
                        case '.':
                            duration = short_torch;
                            break;
                        case '-':
                            duration = long_torch;
                            break;
                        case '|':
                            duration = stop_torch;
                            break;
                        default:
                            duration = long_torch;
                            break;
                    }

                    flashTorch(true, duration);

                    // Delay before turning off the flashlight
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            flashTorch(false, 0);

                            // Delay before processing the next character
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progress.setProgress(index + 1);
                                    flashMessage(message, index + 1);
                                }
                            }, short_torch);
                        }
                    }, duration);
                } else {

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            progress.setVisibility(View.INVISIBLE);

                            plainText.setEnabled(true);
                            encode.setEnabled(true);
                            clearAll.setEnabled(true);
                            pasteText.setEnabled(true);
                            encryptedMessage.setEnabled(true);
                            flashlight.setEnabled(true);
                            back.setEnabled(true);
                        }
                    }, 1000);

                }
            }

            private void flashTorch(boolean on, int duration) {
                try {
                    cameraManager.setTorchMode(getCameraID, on);
                    if (duration > 0) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    cameraManager.setTorchMode(getCameraID, false);
                                } catch (CameraAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, duration);
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });




    }
}