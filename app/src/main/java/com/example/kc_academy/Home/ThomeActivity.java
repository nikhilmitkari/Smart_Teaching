package com.example.kc_academy.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kc_academy.R;

import java.util.Timer;

public class ThomeActivity extends BaseActivity {

    TextView tvName;
    Button btnFiles, btnTest, btnNotice,
            btnProfile, btnSettings, btnAnswer;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thome);
        btnFiles = findViewById(R.id.btn_add);
      //  btnTest = findViewById(R.id.btn_answer);
        btnNotice = findViewById(R.id.btn_Tnotice);
        btnProfile = findViewById(R.id.btn_profile);
        btnSettings =findViewById(R.id.btn_setting);
        btnAnswer = findViewById(R.id.btn_answer);
        tvName = findViewById(R.id.TeacherName);
        String Name = getIntent().getStringExtra("abc");
        tvName.setText(Name);


                btnProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent ab = new Intent(ThomeActivity.this, TeacherProfile.class);
                        startActivity(ab);
                    }
                });

        btnFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ab = new Intent(ThomeActivity.this, AddFiles.class);
                startActivity(ab);
            }
        });

        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ab = new Intent(ThomeActivity.this, TeacherNotification.class);
                startActivity(ab);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent st = new Intent(ThomeActivity.this, TeacherSettings.class);
                startActivity(st);
            }
        });

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent st = new Intent(ThomeActivity.this, TestData.class);
                startActivity(st);
            }
        });
    }

    public void Notice(View view) {
        startActivity(new Intent(ThomeActivity.this, MyMessage.class));

    }
}
/*
 timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               Intent intent = new Intent(ThomeActivity.this, TeacherLogin.class);
                               startActivity(intent);
                               finish();
                           }
                       }, 5000);

 */