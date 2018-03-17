package com.example.shubham.text_to_speech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    EditText et1;
    Button bt1;
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.et);
        bt1 = findViewById(R.id.bt1);
        tts = new TextToSpeech(this , this);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakout();
            }
        });
    }

    private void speakout() {
        CharSequence text = et1.getText();
        tts.speak(text , TextToSpeech.QUEUE_FLUSH , null , "id1");
    }
    @Override
    public void onDestroy(){
        if (tts != null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS)
        {
            int result = tts.setLanguage(Locale.ENGLISH);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS" , "This Language Is Not Supported");
            }
            else {
                bt1.setEnabled(true);
                speakout();
            }
        }
        else {
            Log.e("TTS" , "Initialization Failed");
        }

    }
    };

