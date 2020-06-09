package com.example.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private static int SPLAS_TIME_OUT = 4000;

    private Handler hdlr = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(splash);
                finish();
            }
        }, SPLAS_TIME_OUT);

        final ProgressBar load = (ProgressBar) findViewById(R.id.load);
        final TextView textView = (TextView)findViewById(R.id.text_progress);

        new Thread(new Runnable() {
            int i = 0;

            public void run() {
                while (i < 100) {
                    i += 1;

                    hdlr.post(new Runnable() {
                        public void run() {
                            load.setProgress(i);
                            textView.setText(Integer.toString(i));
                        }
                    });
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}