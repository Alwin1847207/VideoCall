package com.example.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MessagingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Button btn_end = (Button)findViewById(R.id.button2);
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MessagingActivity.this, "Ending Messagnig", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(MessagingActivity.this,HomeActivity.class);
                startActivity(home);
                finish();
            }
        });
    }
}