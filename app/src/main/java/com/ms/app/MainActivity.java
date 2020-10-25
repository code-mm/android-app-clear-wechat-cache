package com.ms.app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    private Button buttonOpenOtherAppPermission;
    private Button buttonWeChatReceiveFiles;
    private Button buttonWeChatPNG;
    private Button buttonWeChatEMOJI;
    private Button buttonWeChatMP3;
    private Button buttonWeChatMP4;
    private Button buttonWeChatImage;
    private Button buttonWeChatVoice2;

    private Button buttonProd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOpenOtherAppPermission = findViewById(R.id.buttonOpenOtherAppPermission);
        buttonWeChatReceiveFiles = findViewById(R.id.buttonWeChatReceiveFiles);
        buttonWeChatPNG = findViewById(R.id.buttonWeChatPNG);
        buttonWeChatEMOJI = findViewById(R.id.buttonWeChatEMOJI);
        buttonWeChatMP3 = findViewById(R.id.buttonWeChatMP3);
        buttonWeChatMP4 = findViewById(R.id.buttonWeChatMP4);
        buttonWeChatImage = findViewById(R.id.buttonWeChatImage);
        buttonWeChatVoice2 = findViewById(R.id.buttonWeChatVoice2);
        buttonProd = findViewById(R.id.buttonProd);

        buttonOpenOtherAppPermission.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        });

        buttonWeChatReceiveFiles.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            intent.putExtra("type", "RECEIVEFILES");
            startActivity(intent);


        });

        buttonWeChatPNG.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            intent.putExtra("type", "PNG");
            startActivity(intent);
        });

        buttonWeChatEMOJI.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            intent.putExtra("type", "EMOJI");
            startActivity(intent);
        });


        buttonWeChatMP3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            intent.putExtra("type", "MP3");
            startActivity(intent);
        });
        buttonWeChatMP4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            intent.putExtra("type", "MP4");
            startActivity(intent);

        });

        buttonWeChatImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            intent.putExtra("type", "IMAGE");
            startActivity(intent);
        });

        buttonWeChatVoice2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            intent.putExtra("type", "VOICE2");
            startActivity(intent);
        });

        buttonProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this,WeChatClearScanActivity.class));

            }
        });


    }

    private static final String TAG = "MainActivity";


}
